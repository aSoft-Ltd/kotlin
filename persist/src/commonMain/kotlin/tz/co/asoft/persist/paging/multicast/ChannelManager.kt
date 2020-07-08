package tz.co.asoft.persist.paging.multicast

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.Flow

internal class ChannelManager<T>(
    private val scope: CoroutineScope,
    private val bufferSize: Int,
    private val piggybackingDownstream: Boolean = false,
    private val onEach: suspend (T) -> Unit,
    private val keepUpstreamAlive: Boolean = false,
    private val upstream: Flow<T>
) {

    suspend fun addDownstream(channel: SendChannel<Message.Dispatch.Value<T>>) =
        actor.send(Message.AddChannel(channel))

    suspend fun removeDownstream(channel: SendChannel<Message.Dispatch.Value<T>>) =
        actor.send(Message.RemoveChannel(channel))

    suspend fun close() = actor.close()

    private val actor = Actor()

    private inner class Actor : StoreRealActor<Message<T>>(scope) {
        private val buffer = Buffer<T>(bufferSize)
        private var producer: SharedFlowProducer<T>? = null
        private var dispatchedValue: Boolean = false
        private var lastDeliveryAck: CompletableDeferred<Unit>? = null
        private val channels = mutableListOf<ChannelEntry<T>>()

        override suspend fun handle(msg: Message<T>) {
            when (msg) {
                is Message.AddChannel -> doAdd(msg)
                is Message.RemoveChannel -> doRemove(msg.channel)
                is Message.Dispatch.Value -> doDispatchValue(msg)
                is Message.Dispatch.Error -> doDispatchError(msg)
                is Message.Dispatch.UpstreamFinished -> doHandleUpstreamClose(msg.producer)
            }
        }

        private fun newProducer() = SharedFlowProducer(scope, upstream, ::send)

        /**
         * We are closing. Do a cleanup on existing channels where we'll close them and also decide
         * on the list of leftovers.
         */
        private fun doHandleUpstreamClose(producer: SharedFlowProducer<T>?) {
            if (this.producer !== producer) {
                return
            }
            val piggyBacked = mutableListOf<ChannelEntry<T>>()
            val leftovers = mutableListOf<ChannelEntry<T>>()
            channels.forEach {
                when {
                    it.receivedValue -> {
                        if (!piggybackingDownstream) {
                            it.close()
                        } else {
                            piggyBacked.add(it)
                        }
                    }
                    dispatchedValue ->
                        // we dispatched a value but this channel didn't receive so put it into
                        // leftovers
                        leftovers.add(it)
                    else -> { // upstream didn't dispatch
                        if (!piggybackingDownstream) {
                            it.close()
                        } else {
                            piggyBacked.add(it)
                        }
                    }
                }
            }
            channels.clear() // empty references
            channels.addAll(leftovers)
            channels.addAll(piggyBacked)
            this.producer = null
            // we only reactivate if leftovers is not empty
            if (leftovers.isNotEmpty()) {
                activateIfNecessary()
            }
        }

        override fun onClosed() {
            channels.forEach {
                it.close()
            }
            channels.clear()
            producer?.cancel()
        }

        /**
         * Dispatch value to all downstream collectors.
         */
        private suspend fun doDispatchValue(msg: Message.Dispatch.Value<T>) {
            onEach(msg.value)
            buffer.add(msg)
            dispatchedValue = true
            if (buffer.isEmpty()) {
                // if a new downstream arrives, we need to ack this so that it won't wait for
                // values that it'll never receive
                lastDeliveryAck = msg.delivered
            }
            channels.forEach {
                it.dispatchValue(msg)
            }
        }

        /**
         * Dispatch an upstream error to downstream collectors.
         */
        private fun doDispatchError(msg: Message.Dispatch.Error<T>) {
            // dispatching error is as good as dispatching value
            dispatchedValue = true
            channels.forEach {
                it.dispatchError(msg.error)
            }
        }

        /**
         * Remove a downstream collector.
         */
        private suspend fun doRemove(channel: SendChannel<Message.Dispatch.Value<T>>) {
            val index = channels.indexOfFirst {
                it.hasChannel(channel)
            }
            if (index >= 0) {
                channels.removeAt(index)
                if (channels.isEmpty() && !keepUpstreamAlive) {
                    producer?.cancelAndJoin()
                }
            }
        }

        /**
         * Add a new downstream collector
         */
        private suspend fun doAdd(msg: Message.AddChannel<T>) {
            addEntry(
                entry = ChannelEntry(
                    channel = msg.channel
                )
            )
            activateIfNecessary()
        }

        private fun activateIfNecessary() {
            if (producer == null) {
                producer = newProducer()
                dispatchedValue = false
                producer!!.start()
            }
        }

        /**
         * Internally add the new downstream collector to our list, send it anything buffered.
         */
        private suspend fun addEntry(entry: ChannelEntry<T>) {
            val new = channels.none {
                it.hasChannel(entry)
            }
            check(new) {
                "$entry is already in the list."
            }
            check(!entry.receivedValue) {
                "$entry already received a value"
            }
            channels.add(entry)
            if (buffer.items.isNotEmpty()) {
                // if there is anything in the buffer, send it
                buffer.items.forEach {
                    entry.dispatchValue(it)
                }
            } else {
                // unlock upstream since we now have a downstream that needs values
                lastDeliveryAck?.complete(Unit)
            }
        }
    }

    /**
     * Holder for each downstream collector
     */
    internal data class ChannelEntry<T>(
        /**
         * The channel used by the collector
         */
        private val channel: SendChannel<Message.Dispatch.Value<T>>,
        /**
         * Tracking whether we've ever dispatched a value or an error to downstream
         */
        private var _receivedValue: Boolean = false
    ) {
        val receivedValue
            get() = _receivedValue

        suspend fun dispatchValue(value: Message.Dispatch.Value<T>) {
            _receivedValue = true
            channel.send(value)
        }

        fun dispatchError(error: Throwable) {
            _receivedValue = true
            channel.close(error)
        }

        fun close() {
            channel.close()
        }

        fun hasChannel(channel: SendChannel<Message.Dispatch.Value<T>>) = this.channel === channel

        fun hasChannel(entry: ChannelEntry<T>) = this.channel === entry.channel
    }

    /**
     * Messages accepted by the [ChannelManager].
     */
    sealed class Message<T> {
        class AddChannel<T>(
            val channel: SendChannel<Dispatch.Value<T>>
        ) : Message<T>()
        class RemoveChannel<T>(val channel: SendChannel<Dispatch.Value<T>>) : Message<T>()

        sealed class Dispatch<T> : Message<T>() {
            /**
             * Upstream dispatched a new value, send it to all downstream items
             */
            class Value<T>(
                /**
                 * The value dispatched by the upstream
                 */
                val value: T,
                /**
                 * Ack that is completed by all receiver. Upstream producer will await this before asking
                 * for a new value from upstream
                 */
                val delivered: CompletableDeferred<Unit>
            ) : Dispatch<T>()

            /**
             * Upstream dispatched an error, send it to all downstream items
             */
            class Error<T>(
                /**
                 * The error sent by the upstream
                 */
                val error: Throwable
            ) : Dispatch<T>()

            class UpstreamFinished<T>(
                /**
                 * SharedFlowProducer finished emitting
                 */
                val producer: SharedFlowProducer<T>
            ) : Dispatch<T>()
        }
    }
}

/**
 * Buffer implementation for any late arrivals.
 */
private interface Buffer<T> {
    fun add(item: ChannelManager.Message.Dispatch.Value<T>)
    fun isEmpty() = items.isEmpty()
    val items: Collection<ChannelManager.Message.Dispatch.Value<T>>
}

/**
 * Default implementation of buffer which does not buffer anything.
 */
private class NoBuffer<T> : Buffer<T> {
    override val items: Collection<ChannelManager.Message.Dispatch.Value<T>>
        get() = emptyList()

    // ignore
    override fun add(item: ChannelManager.Message.Dispatch.Value<T>) = Unit
}

/**
 * Create a new buffer insteance based on the provided limit.
 */
@Suppress("FunctionName")
private fun <T> Buffer(limit: Int): Buffer<T> = if (limit > 0) {
    BufferImpl(limit)
} else {
    NoBuffer()
}

/**
 * A real buffer implementation that has a FIFO queue.
 */
@OptIn(ExperimentalStdlibApi::class)
private class BufferImpl<T>(private val limit: Int) :
    Buffer<T> {

    override val items =
        ArrayDeque<ChannelManager.Message.Dispatch.Value<T>>(limit.coerceAtMost(10))

    override fun add(item: ChannelManager.Message.Dispatch.Value<T>) {
        while (items.size >= limit) {
            items.first()
        }
        items.addLast(item)
    }
}

internal fun <T> ChannelManager.Message.Dispatch.Value<T>.markDelivered() =
    delivered.complete(Unit)
