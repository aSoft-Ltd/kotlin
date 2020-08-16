package tz.co.asoft

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

open class IntentBus<I : Any>(initialIntent: I? = null) : CoroutineScope by CoroutineScope(SupervisorJob()) {
    private val INTENT_BUS = MutableStateFlow(initialIntent)
    fun post(i: I) = launch {
        INTENT_BUS.value = i
        delay(1)
        INTENT_BUS.value = null
    }

    suspend fun collect(collector: suspend (I) -> Unit) {
        INTENT_BUS.filterNotNull().collect(collector)
    }
}