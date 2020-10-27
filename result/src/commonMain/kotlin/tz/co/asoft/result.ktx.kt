@file:Suppress("NOTHING_TO_INLINE")

package tz.co.asoft

import kotlinx.serialization.KSerializer

fun <T> Either.Companion.stringify(
    serializer: KSerializer<T>,
    res: Result<T>
): String = stringify(serializer, Failure.serializer(), res)

fun <T> Either.Companion.parse(
    serializer: KSerializer<T>,
    json: String
): Result<T> = try {
    parse(serializer, Failure.serializer(), json)
} catch (c: Throwable) {
    Either.Right(
        Failure(
            error = c.message ?: c.cause?.message ?: "Failed to serialize json",
            type = c::class.simpleName ?: "Unknown Type",
            reason = c.cause?.message,
            stackTrace = c.cause?.cause?.message
        )
    )
}

fun <T> Result<T>.response(): T = when (this) {
    is Either.Left -> value
    is Either.Right -> throw Exception(value.error)
}

inline fun <T> Result<T>.responseOrNull(): T? = try {
    response()
} catch (e: Exception) {
    null
}

inline fun <T> Result<T>.catch(handler: (Exception) -> Unit): Result<T> {
    if (this is Either.Right) {
        handler(Exception(value.error))
    }
    return this
}

inline fun <T> Result<T>.collect(handler: (T) -> Unit) {
    if (this is Either.Left) {
        handler(value)
    }
}

fun <T> Success(value: T): Result<T> = Either.Left(value)

inline fun <T> catching(block: () -> T): Result<T> = try {
    Either.Left(block())
} catch (e: Exception) {
    Either.Right(e.asFailure())
}

fun Throwable.asFailure() = Failure(
    error = message ?: cause?.message ?: "Unknown Error",
    type = this::class.simpleName ?: "Unknown type",
    reason = cause?.message,
    stackTrace = cause?.cause?.message
)

inline fun <T> T.asSuccess(): Result<T> = Success(this)