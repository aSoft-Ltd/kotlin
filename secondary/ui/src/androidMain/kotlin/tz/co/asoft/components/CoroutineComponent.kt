package tz.co.asoft.components

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@Deprecated("Use ControlledComopnent Instead")
abstract class CoroutineComponent<P : CProps, S : Any> : ScopedComponent<P, CState>() {
    protected fun Channel<S>.bind() = launch { consumeAsFlow().bind() }

    protected fun Flow<S>.bind() = launch { onEach { ui -> render(ui) }.collect() }

    abstract fun render(ui: S): Any?
}

suspend fun <T> Flow<T>.every(action: suspend (T) -> Unit) = onEach(action).collect()