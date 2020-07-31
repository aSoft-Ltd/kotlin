package tz.co.asoft

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList

suspend fun <T> Flow<T>.collectTo(ui: MutableStateFlow<T>) = collect { ui.value = it }

suspend fun <T> Flow<Task<T>>.complete(): T = when (val data = toList().last()) {
    is Task.Failed -> throw Exception("Task didn't complete with data: ${data.cause.message}")
    is Task.Progress -> throw Exception("Tasks never completes. It is still in progress")
    is Task.Completed -> data.data
}