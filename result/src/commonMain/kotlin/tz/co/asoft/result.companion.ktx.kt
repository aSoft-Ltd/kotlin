package tz.co.asoft

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

fun <T> Either.Companion.stringify(
    serializer: KSerializer<T>,
    res: Result<T>
): String = stringify(serializer, Failure.serializer(), res)

fun <L, R> Either.Companion.stringifyEither(
    leftSerializer: KSerializer<L>,
    rightSerializer: KSerializer<R>,
    res: Result<Either<L, R>>
): String = try {
    stringify(leftSerializer, rightSerializer, res.response())
} catch (c: Throwable) {
    Json.stringify(
        Failure.serializer(), Failure(
            error = c.message ?: c.cause?.message ?: "Failed to serialize json",
            type = c::class.simpleName ?: "Unknown Type",
            reason = c.cause?.message,
            stackTrace = c.cause?.cause?.message
        )
    )
}

fun <T> Either.Companion.parse(
    serializer: KSerializer<T>,
    json: String
): Result<T> = try {
    parse(serializer, Failure.serializer(), json)
} catch (c: Throwable) {
    Either.Right(
        Failure(
            error = c.message ?: c.cause?.message ?: "Failed to parse json",
            type = c::class.simpleName ?: "Unknown Type",
            reason = c.cause?.message,
            stackTrace = c.cause?.cause?.message
        )
    )
}

fun <L, R> Either.Companion.parseEither(
    leftSerializer: KSerializer<L>,
    rightSerializer: KSerializer<R>,
    json: String
): Result<Either<L, R>> = try {
    parse(leftSerializer, rightSerializer, json).asSuccess()
} catch (c: Throwable) {
    Either.Right(
        Failure(
            error = c.message ?: c.cause?.message ?: "Failed to parse json",
            type = c::class.simpleName ?: "Unknown Type",
            reason = c.cause?.message,
            stackTrace = c.cause?.cause?.message
        )
    )
}