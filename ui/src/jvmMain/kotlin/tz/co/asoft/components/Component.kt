package tz.co.asoft.components

import tz.co.asoft.platform.core.Activity
import tz.co.asoft.platform.core.Ctx

@Deprecated("Use from the component package")
actual abstract class Component<P : CProps, S : CState> {
    actual constructor()

    actual constructor(props: P)

    companion object {
        val globalActivity = Activity()
    }

    actual val activity by lazy { globalActivity }

    actual val ctx: Ctx by lazy { activity }

    actual open fun onReady() {

    }

    actual open fun onResumed() {

    }

    actual open fun onPaused() {

    }

    protected actual fun setState(builder: S.() -> Unit) {}

    actual open fun onFinished() {

    }

    actual open fun alert(msg: Any?) = println(msg)
}