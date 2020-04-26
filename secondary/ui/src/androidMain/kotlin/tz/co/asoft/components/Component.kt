package tz.co.asoft.components

import android.os.Bundle
import android.view.View
import tz.co.asoft.components.android.AndroidComponent
import tz.co.asoft.components.android.child
import tz.co.asoft.persist.result.catching
import tz.co.asoft.platform.core.Activity
import tz.co.asoft.tools.alert
import tz.co.asoft.ui.action.Action

@Deprecated("Use component form tz.co.asoft.component")
actual abstract class Component<P : CProps, S : CState> actual constructor() : AndroidComponent<P, S>() {

    actual val activity: Activity get() = requireActivity()

    actual val ctx get() = activity.applicationContext!!

    actual constructor(props: P) : this() {
        this.realProps = props
    }

    actual open fun alert(msg: Any?) {
        ctx.alert(msg)
    }

    actual open fun onReady() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            onReady()
        }
    }

    override fun onResume() {
        super.onResume()
        onResumed()
    }

    actual open fun onResumed() {

    }

    override fun onPause() {
        onPaused()
        super.onPause()
    }

    actual open fun onPaused() {

    }

    actual final override fun setState(builder: S.() -> Unit) {
        super.setState(builder)
    }

    actual open fun onFinished() {}

    override fun onDestroy() {
        onFinished()
        super.onDestroy()
    }

    open fun showLoading(message: String) = child(Loading::class.java, Loading.Props()) {
        catching {
            attrs {
                msg = message
                theme = props.theme
            }
        }
    }

    open fun showError(message: String, actions: List<Action> = listOf()) = child(Error::class.java, Error.Props()) {
        attrs {
            msg = message
            theme = props.theme
        }
    }
}