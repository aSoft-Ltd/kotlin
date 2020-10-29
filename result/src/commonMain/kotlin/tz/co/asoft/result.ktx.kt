@file:Suppress("NOTHING_TO_INLINE")

package tz.co.asoft

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

inline val <T> Result<T>.status: ResultStatus
    get() = when (this) {
        is Either.Left -> ResultStatus.Success
        is Either.Right -> ResultStatus.Failure
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

fun <T> Throwable.toFailure(): Result<T> = Failure(
    error = message ?: cause?.message ?: "Unknown Error",
    type = this::class.simpleName ?: "Unknown type",
    reason = cause?.message,
    stackTrace = cause?.cause?.message
).toResult()

inline fun <T> T.asSuccess(): Result<T> = Success(this)

@OptIn(ExperimentalContracts::class)
fun <T> Result<T>.isSuccess(): Boolean {
    contract {
        returns(true) implies (this@isSuccess is Either.Left)
    }
    return this is Either.Left
}

@OptIn(ExperimentalContracts::class)
fun <T> Result<T>.isFailure(): Boolean {
    contract {
        returns(true) implies (this@isFailure is Either.Right)
    }
    return this is Either.Right
}