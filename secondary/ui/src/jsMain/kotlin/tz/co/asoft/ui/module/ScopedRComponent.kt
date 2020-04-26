package tz.co.asoft.ui.module

import kotlinext.js.jsObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import react.*
import tz.co.asoft.components.CProps
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass

abstract class ScopedRComponent<P : RProps, S : RState> : RComponent<P, S>, CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Unconfined

    constructor() : super() {
        state = jsObject { init() }
    }

    constructor(props: P) : super(props) {
        state = jsObject { init(props) }
    }

    fun syncState(block: suspend S.() -> Unit) {
        launch {
            try {
                state.block()
                setState {}
            } catch (err: Throwable) {
                console.log(err.message)
            }
        }
    }

    override fun componentWillUnmount() {
        job.cancel()
    }
}

abstract class ObservingRComponent<T, P : RProps, S : RState> : ScopedRComponent<P, S> {

    constructor() : super() {
        state = jsObject { init() }
    }

    constructor(props: P) : super(props) {
        state = jsObject { init(props) }
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
    }
}

abstract class ObservingComponent<T, P : RProps, S : RState> : ObservingRComponent<MutableList<T>, P, S> {
    constructor() : super() {
        state = jsObject { init() }
    }

    constructor(props: P) : super(props) {
        state = jsObject { init(props) }
    }
}

inline fun <P : CProps, reified T : Component<P, *>> RBuilder.child(
        clazz: KClass<T>,
        props: P? = null,
        noinline handler: RHandler<P>
): ReactElement {
    val p: P = props ?: try {
        var newProps = T::class.js.asDynamic().Props.unsafeCast<P>()
        if (newProps == undefined) {
            newProps = T::class.js.asDynamic().Props().unsafeCast<P>()
        }
        if (newProps == undefined) {
            throw Throwable("props is not set")
        }
        newProps
    } catch (c: Throwable) {
        CProps().unsafeCast<P>()
    }
    return child(clazz.js, p, handler)
}