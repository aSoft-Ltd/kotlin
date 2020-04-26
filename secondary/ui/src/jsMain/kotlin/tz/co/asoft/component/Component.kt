package tz.co.asoft.component

import kotlinx.coroutines.*
import react.*
import tz.co.asoft.LifeCycleMethods
import tz.co.asoft.components.ModuleProps
import tz.co.asoft.platform.core.Activity
import tz.co.asoft.platform.core.Ctx
import tz.co.asoft.platform.core.FragmentActivity
import tz.co.asoft.rx.LiveData
import tz.co.asoft.ui.action.Action
import tz.co.asoft.ui.react.composites.async.Error
import tz.co.asoft.ui.react.composites.async.Loading
import kotlin.coroutines.CoroutineContext

abstract class Component<P : RProps, S : RState> : RComponent<P, S>, CoroutineScope, LifeCycleMethods {
    constructor() : super()
    constructor(props: P) : super(props)

    companion object {
        val globalActivity = FragmentActivity()
    }

    val act get() = globalActivity

    val ctx: Ctx get() = globalActivity

    protected val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    protected fun syncState(context: CoroutineContext = coroutineContext, buildState: suspend S.() -> Unit) {
        launch(context) {
            state.buildState()
            setState { }
        }
    }

    override fun componentDidMount() {
        onReady()
        onResume()
    }

    fun <T> LiveData<T>.observe(action: (T) -> Unit) = onChange(this@Component, action)

    fun RBuilder.showLoading(msg: String) = child(Loading::class.js, Loading.Props()) {
        attrs.msg = msg
        props.unsafeCast<ModuleProps?>()?.theme?.let { attrs.theme = it }
    }

    fun RBuilder.showError(msg: String, actions: List<Action> = listOf()) = child(Error::class.js, Error.Props()) {
        attrs.msg = msg
        attrs.actions = actions
        props.unsafeCast<ModuleProps?>()?.theme?.let { attrs.theme = it }
    }

    override fun componentWillUnmount() {
        job.cancel()
        onPause()
        onDestroy()
    }
}