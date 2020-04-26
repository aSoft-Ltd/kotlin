package tz.co.asoft.components

import react.RBuilder
import react.RComponent
import react.setState
import tz.co.asoft.platform.core.Activity
import tz.co.asoft.platform.core.Ctx
import tz.co.asoft.ui.action.Action
import tz.co.asoft.ui.react.composites.async.Error
import tz.co.asoft.ui.react.composites.async.Loading
import kotlin.browser.window

@Deprecated("Use Component")
actual abstract class Component<P : CProps, S : CState> : RComponent<P, S> {

    actual constructor() : super()
    actual constructor(props: P) : super(props)

    companion object {
        val globalActivity = Activity()
    }

    actual val activity by lazy { globalActivity }

    actual val ctx: Ctx by lazy { activity }

    actual open fun onReady() {}

    actual open fun alert(msg: Any?) = window.alert(msg.toString())

    override fun componentDidMount() {
        onReady()
        onResumed()
    }

    actual open fun onResumed() {}

    actual open fun onPaused() {}

    protected actual fun setState(builder: S.() -> Unit) {
        setState(buildState = builder)
    }

    actual open fun onFinished() {}

    override fun componentWillUnmount() {
        onPaused()
        onFinished()
    }

    fun RBuilder.showLoading(msg: String) = child(Loading::class.js, Loading.Props()) {
        attrs.msg = msg
        props.unsafeCast<ModuleProps?>()?.theme?.let { attrs.theme = it }
    }

    fun RBuilder.showError(msg: String, actions: List<Action> = listOf()) = child(Error::class.js, Error.Props()) {
        attrs.msg = msg
        attrs.actions = actions
        props.unsafeCast<ModuleProps?>()?.theme?.let { attrs.theme = it }
    }

    override fun RBuilder.render() {
        child(Loading::class, Loading.Props()) {}
    }
}