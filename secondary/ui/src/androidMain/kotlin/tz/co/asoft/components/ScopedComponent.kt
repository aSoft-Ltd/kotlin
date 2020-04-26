package tz.co.asoft.components

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@Deprecated("A Component is automatically scoped")
actual abstract class ScopedComponent<P : CProps, S : CState> : Component<P, S>, CoroutineScope {
    actual constructor() : super()
    actual constructor(props: P) : super(props)

    actual val job = Job()
    actual override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    protected actual fun syncState(context: CoroutineContext, buildState: suspend S.() -> Unit) {
        launch(context) {
            state?.apply { buildState() }
            executeRender()
        }
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
        job.complete()
        super.onFinished()
    }
}