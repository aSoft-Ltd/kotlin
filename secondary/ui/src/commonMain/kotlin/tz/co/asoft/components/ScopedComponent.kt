package tz.co.asoft.components

import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

@Deprecated("A Component is automatically scoped")
expect abstract class ScopedComponent<P : CProps, S : CState> : Component<P, S>, CoroutineScope {
    constructor()
    constructor(props: P)

    protected val job: CompletableJob
    override val coroutineContext: CoroutineContext

    protected fun syncState(context: CoroutineContext = coroutineContext, buildState: suspend S.() -> Unit)
}