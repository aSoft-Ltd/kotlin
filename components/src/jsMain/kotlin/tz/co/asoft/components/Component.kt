package tz.co.asoft.components

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import react.RComponent
import react.RProps
import react.RState
import react.setState
import kotlin.coroutines.CoroutineContext

abstract class Component<P : RProps, S : RState> : RComponent<P, S>, CoroutineScope {
    constructor() : super()
    constructor(props: P) : super(props)

    protected val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Default + job

    protected fun syncState(
        context: CoroutineContext = coroutineContext,
        buildState: suspend S.() -> Unit
    ) {
        launch(context) {
            state.buildState()
            setState { }
        }
    }

    fun <T> Flow<T>.observe(
        context: CoroutineContext = coroutineContext,
        onChange: suspend (T) -> Unit
    ) = launch(context) { collect { onChange(it) } }

    override fun componentWillUnmount() {
        cancel()
    }
}