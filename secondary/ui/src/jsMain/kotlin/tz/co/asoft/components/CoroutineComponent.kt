package tz.co.asoft.components

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import react.RBuilder
import tz.co.asoft.components.CoroutineComponent.UIState

@Deprecated("Use Controlled Component")
abstract class CoroutineComponent<P : CProps, S : Any> : ScopedComponent<P, UIState<S>>() {
    class UIState<S>(var ui: S?) : CState()

    init {
        state = UIState(null)
    }

    protected fun Channel<S>.bind() = launch { consumeAsFlow().bind() }

    protected fun Flow<S>.bind() = launch { onEach { setState { ui = it } }.collect() }

    abstract fun RBuilder.render(ui: S)

    override fun RBuilder.render(): dynamic = state.ui?.let { render(it) }
}