package tz.co.asoft

import kotlinx.coroutines.flow.Flow
import react.RBuilder
import react.RProps
import react.RState
import react.setState
import tz.co.asoft.VComponent.UIState

abstract class VComponent<P : RProps, I, S, V : VModel<I, S>> : Component<P, UIState<S>> {
    abstract val viewModel: V

    class UIState<S>(var ui: S?) : RState

    constructor() : super()

    constructor(props: P) : super(props)

    init {
        state = UIState(null)
    }

    abstract fun RBuilder.render(ui: S): Any

    override fun RBuilder.render() {
        state.ui?.let { render(it) }
    }

    override fun componentDidMount() {
        viewModel.ui.bind()
    }

    fun Flow<S>.bind() = observe { setState { ui = it } }

    open fun post(i: I) = viewModel.post(i)
}