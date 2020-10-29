package tz.co.asoft

typealias Result<T> = Either<T, Failure>

fun <T> Either.Companion.Failure(
    error: String,
    type: String,
    reason: String? = null
): Result<T> = Failure(error, type, reason, null).toResult()