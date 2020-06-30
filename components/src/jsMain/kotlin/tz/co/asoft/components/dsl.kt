package tz.co.asoft.components

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import react.getValue
import react.setValue
import react.useEffectWithCleanup
import react.useState

fun <S> Flow<S>.asState(initial: S): S {
    var state by useState(initial)
    useEffectWithCleanup {
        val scope = CoroutineScope(SupervisorJob())
        scope.launch { collect { if (state != it) state = it } };
        { scope.cancel() }
    }
    return state
}