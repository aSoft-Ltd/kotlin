package tz.co.asoft.tools

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.map

sealed class Task<T> {
    class Progress<T>(val pct: Int, val msg: String = "") : Task<T>() {
        override fun toString() = "$pct%: $msg"
    }

    class Failed<T>(val cause: Throwable) : Task<T>()
    class Completed<T>(val data: T) : Task<T>()
}

fun <T, S> Task<T>.map(
    from: Int = 0,
    to: Int = 100,
    transform: (T) -> S
): Task<S> = when (this) {
    is Task.Progress -> Task.Progress(from + ((to - from) * pct / 100), msg)
    is Task.Failed -> Task.Failed(cause)
    is Task.Completed -> Task.Completed(transform(data))
}

fun <T, S> Task<T>.map(
    range: IntRange,
    transform: (T) -> S
): Task<S> = map(range.first, range.last, transform)

fun <T> Task<T>.map(from: Int, to: Int): Task<T> = when (this) {
    is Task.Progress -> Task.Progress(from + ((to - from) * pct / 100), msg)
    is Task.Failed -> Task.Failed(cause)
    is Task.Completed -> Task.Completed(data)
}

fun <T, S> Task<T>.mapProgress(from: Int, to: Int): Task<S> = when (this) {
    is Task.Progress -> Task.Progress(from + ((to - from) * pct / 100), msg)
    is Task.Failed -> Task.Failed(cause)
    is Task.Completed -> Task.Progress(to)
}

fun <T, S> Task<T>.mapProgress(range: IntRange): Task<S> = mapProgress(range.first, range.last)

fun <T, S> Flow<Task<T>>.map(
    from: Int = 0,
    to: Int = 100,
    transform: (T) -> S
) = map { it.map(from, to, transform) }

fun <T, S> Flow<Task<T>>.map(
    range: IntRange,
    transform: (T) -> S
) = map(range.first, range.last, transform)

fun <T> Flow<Task<T>>.map(from: Int = 0, to: Int = 100): Flow<Task<T>> = map { it.map(from, to) }

fun <T, S> Flow<Task<T>>.map(range: IntRange) = map(range.first, range.last)

fun <T, S> Flow<Task<T>>.mapProgress(from: Int = 0, to: Int = 100) =
    map { it.mapProgress<T, S>(from, to) }

fun <T, S> Flow<Task<T>>.mapProgress(range: IntRange) = map { it.mapProgress<T, S>(range) }

fun <T> flowTask(block: suspend FlowCollector<Task<T>>.() -> Unit) = flow(block).catch { emit(it) }

suspend fun <T> FlowCollector<Task<T>>.emit(
    pct: Int,
    msg: String = ""
) = emit(Task.Progress(pct, msg))

suspend fun <T> FlowCollector<Task<T>>.emit(cause: Throwable) = emit(Task.Failed(cause))

suspend fun <T> FlowCollector<Task<T>>.emit(data: T) = emit(Task.Completed(data))

suspend fun <T> Flow<T>.collectTo(ui: MutableStateFlow<T>) = collect { ui.value = it }

suspend fun <T> Flow<Task<T>>.complete(): T = (toList().last() as? Task.Completed<T>)?.data
    ?: throw Exception("FlowTask didn't complete with data")