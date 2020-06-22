package tz.co.asoft.components

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

actual abstract class ScopedComponent<P : CProps, S : CState> : Component<P, S>, CoroutineScope {

    actual constructor()

    actual constructor(props: P)

    protected actual val job = Job()
    actual override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    protected actual fun syncState(context: CoroutineContext, buildState: suspend S.() -> Unit) {

    }

    override fun onReady() {
        super.onReady()
    }

    override fun onResumed() {
        super.onResumed()
    }

    override fun onPaused() {
        super.onPaused()
    }

    override fun onFinished() {
        super.onFinished()
    }
}