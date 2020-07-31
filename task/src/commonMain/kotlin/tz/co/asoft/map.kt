package tz.co.asoft

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.map

fun <T, S> Task<T>.map(
    from: Int = 0,
    to: Int = 100,
    transform: (T) -> S
): Task<S> = when (this) {
    is Task.Progress -> Task.Progress(
        from + ((to - from) * pct / 100),
        msg
    )
    is Task.Failed -> Task.Failed(
        cause
    )
    is Task.Completed -> Task.Completed(
        transform(data)
    )
}

fun <T, S> Task<T>.map(
    range: IntRange,
    transform: (T) -> S
): Task<S> = map(range.first, range.last, transform)

fun <T> Task<T>.map(from: Int, to: Int): Task<T> = when (this) {
    is Task.Progress -> Task.Progress(
        from + ((to - from) * pct / 100),
        msg
    )
    is Task.Failed -> Task.Failed(
        cause
    )
    is Task.Completed -> Task.Completed(
        data
    )
}

fun <T, S> Task<T>.mapProgress(from: Int, to: Int): Task<S> = when (this) {
    is Task.Progress -> Task.Progress(
        from + ((to - from) * pct / 100),
        msg
    )
    is Task.Failed -> Task.Failed(
        cause
    )
    is Task.Completed -> Task.Progress(
        to
    )
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