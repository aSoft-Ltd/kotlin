package tz.co.asoft.component

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import tz.co.asoft.component.ControlledComponent.UIState
import tz.co.asoft.rx.LiveData
import tz.co.asoft.viewmodel.ViewModel

abstract class ControlledComponent<P : Any, I, S, V : ViewModel<I, S>> : Component<P, UIState<S>>() {
    abstract val viewModel: V

    class UIState<S> {
        var ui: S? = null
    }

    init {
        state = UIState()
    }

    fun post(i: I) = launch { viewModel.post(i) }

    override fun onReady() {
        super.onReady()
        viewModel.ui.bind()
    }

    fun LiveData<S>.bind() = observe { setState { ui = it } }

    @Deprecated("Do not bund channels to ui")
    fun Channel<S>.bind() = launch { consumeAsFlow().bind() }

    @Deprecated("Do not bund flows to ui")
    fun Flow<S>.bind() = launch { onEach { setState { ui = it } }.collect() }

    abstract fun render(ui: S)

    override fun render() {
        state.ui?.let { render(it) }
    }
}