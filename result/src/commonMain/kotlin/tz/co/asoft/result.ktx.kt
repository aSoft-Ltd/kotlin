@file:Suppress("NOTHING_TO_INLINE")

package tz.co.asoft

fun <T> Result<T>.response(): T = when (this) {
    is Result.Success -> data
    is Result.Failure -> throw Exception(msg)
}

inline fun <T> Result<T>.responseOrNull(): T? = try {
    response()
} catch (e: Exception) {
    null
}

inline fun <T> Result<T>.catch(handler: (Exception) -> Unit): Result<T> {
    if (this is Result.Failure) {
        handler(Exception(msg))
    }
    return this
}

inline fun <T> Result<T>.collect(handler: (T) -> Unit) {
    if (this is Result.Success) {
        handler(data)
    }
}

inline fun <T> catching(block: () -> T) = try {
    Result.Success(block())
} catch (e: Exception) {
    e.asFailure<T>()
}

fun <T> Throwable.asFailure() = Result.Failure<T>(
    msg = message ?: cause?.message ?: "Unknown Error",
    type = this::class.simpleName ?: "Unknown type",
    reason = cause?.message,
    stackTrace = cause?.cause?.message
)

inline fun <T> T.asSuccess() = Result.Success(this)