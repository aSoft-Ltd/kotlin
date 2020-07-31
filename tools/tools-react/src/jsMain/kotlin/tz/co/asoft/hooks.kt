package tz.co.asoft

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import react.*
import kotlin.coroutines.CoroutineContext
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun useUIID(name: String): UIID {
    val id = UIID.getId(name)
    useEffectWithCleanup(listOf()) { { id.release() } }
    return id
}

fun <T> StateFlow<T>.asState(context: CoroutineContext = Dispatchers.Main) = asState(value, context)

operator fun <T> StateFlow<T>.getValue(thisRef: Any?, property: KProperty<*>): T = asState()

fun <T> Flow<T>.asState(initialState: T, context: CoroutineContext = Dispatchers.Main): T {
    var state by useState(initialState)
    useScope(context).launch { collect { state = it } }
    return state
}

fun useScope(context: CoroutineContext = Dispatchers.Main): CoroutineScope {
    val scope = CoroutineScope(context)
    useEffectWithCleanup(listOf()) { { scope.cancel() } }
    return scope
}