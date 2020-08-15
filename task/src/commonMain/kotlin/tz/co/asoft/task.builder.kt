package tz.co.asoft

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

fun <T> flowTask(block: suspend FlowCollector<Task<T>>.() -> Unit) = flow(block).catch { emit(it) }