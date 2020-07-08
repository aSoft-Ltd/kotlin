package tz.co.asoft.persist.paging

import tz.co.asoft.persist.paging.RemoteMediator.InitializeAction.LAUNCH_INITIAL_REFRESH
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.scan

internal class PageFetcher<Key : Any, Value : Any>(
    private val pagingSourceFactory: () -> PagingSource<Key, Value>,
    private val initialKey: Key?,
    private val config: PagingConfig,
    remoteMediator: RemoteMediator<Key, Value>? = null
) {
    private val remoteMediatorAccessor = remoteMediator?.let { RemoteMediatorAccessor(it) }

    private val refreshChannel = ConflatedBroadcastChannel<Boolean>()

    private val retryChannel = ConflatedBroadcastChannel<Unit>()

    val flow: Flow<PagingData<Value>> = channelFlow {
        refreshChannel.asFlow()
            .onStart {
                emit(remoteMediatorAccessor?.initialize() == LAUNCH_INITIAL_REFRESH)
            }
            .scan(null) { previousGeneration: PageFetcherSnapshot<Key, Value>?,
                          triggerRemoteRefresh ->
                val pagingSource = pagingSourceFactory()

                check(pagingSource !== previousGeneration?.pagingSource) {
                    """
                    An instance of PagingSource was re-used when Pager expected to create a new
                    instance. Ensure that the pagingSourceFactory passed to Pager always returns a
                    new instance of PagingSource.
                    """.trimIndent()
                }

                val initialKey = previousGeneration?.refreshKeyInfo()
                    ?.let { pagingSource.getRefreshKey(it) }
                    ?: initialKey

                pagingSource.registerInvalidatedCallback(::invalidate)
                previousGeneration?.pagingSource?.unregisterInvalidatedCallback(::invalidate)
                previousGeneration?.pagingSource?.invalidate() // Note: Invalidate is idempotent.
                previousGeneration?.close()

                PageFetcherSnapshot(
                    initialKey = initialKey,
                    pagingSource = pagingSource,
                    config = config,
                    retryFlow = retryChannel.asFlow(),
                    triggerRemoteRefresh = triggerRemoteRefresh,
                    remoteMediatorAccessor = remoteMediatorAccessor,
                    invalidate = this@PageFetcher::refresh
                )
            }
            .filterNotNull()
            .mapLatest { generation ->
                PagingData(generation.pageEventFlow, PagerUiReceiver(generation, retryChannel))
            }
            .collect { send(it) }
    }

    fun refresh() {
        refreshChannel.offer(true)
    }

    private fun invalidate() {
        refreshChannel.offer(false)
    }

    inner class PagerUiReceiver<Key : Any, Value : Any>(
        private val pageFetcherSnapshot: PageFetcherSnapshot<Key, Value>,
        private val retryChannel: SendChannel<Unit>
    ) : UiReceiver {
        override fun addHint(hint: ViewportHint) = pageFetcherSnapshot.addHint(hint)

        override fun retry() {
            retryChannel.offer(Unit)
        }

        override fun refresh() = this@PageFetcher.refresh()
    }
}
