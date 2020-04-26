package tz.co.asoft.components

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import tz.co.asoft.rx.LiveData
import kotlin.coroutines.CoroutineContext

@Deprecated("A Component is automatically scoped")
actual abstract class ScopedComponent<P : CProps, S : CState> : Component<P, S>, CoroutineScope {
    actual constructor() : super()
    actual constructor(props: P) : super(props)

    protected actual val job = Job()

    actual override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    protected actual fun syncState(context: CoroutineContext, buildState: suspend S.() -> Unit) {
        launch(context) {
            state.buildState()
            setState { }
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