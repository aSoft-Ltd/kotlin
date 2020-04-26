package tz.co.asoft.components

import tz.co.asoft.platform.core.Activity
import tz.co.asoft.platform.core.Ctx

@Deprecated("Use Component from the component package")
expect abstract class Component<P : CProps, S : CState> {
    constructor()
    constructor(props: P)

    val activity: Activity
    val ctx: Ctx

    open fun alert(msg: Any?)

    open fun onReady()
    open fun onResumed()
    open fun onPaused()
    protected fun setState(builder: S.() -> Unit)
    open fun onFinished()
}