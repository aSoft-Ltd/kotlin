package tz.co.asoft.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow

abstract class VModel<in I, S>(initialState: S) : PlatformVModel(), CoroutineScope by CoroutineScope(SupervisorJob() + Dispatchers.Default) {
    val ui = MutableStateFlow(initialState)
    abstract fun post(i: I): Any
}