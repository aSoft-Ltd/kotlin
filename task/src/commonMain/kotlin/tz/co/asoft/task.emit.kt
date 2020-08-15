package tz.co.asoft

import kotlinx.coroutines.flow.FlowCollector

suspend fun <T> FlowCollector<Task<T>>.emit(
    pct: Int,
    msg: String = ""
) = emit(Task.Progress(pct, msg))

suspend fun <T> FlowCollector<Task<T>>.emit(cause: Throwable) = emit(Task.Failed(cause))

suspend fun <T> FlowCollector<Task<T>>.emit(data: T) = emit(Task.Completed(data))