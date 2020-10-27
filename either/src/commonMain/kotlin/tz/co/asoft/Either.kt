package tz.co.asoft

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

sealed class Either<out L, out R> {
    data class Left<L, R>(val value: L) : Either<L, R>()
    data class Right<L, R>(val value: R) : Either<L, R>()

    companion object {
        fun <L, R> stringify(
            leftSerializer: KSerializer<L>,
            rightSerializer: KSerializer<R>,
            e: Either<L, R>
        ) = when (e) {
            is Left -> Json.stringify(leftSerializer, e.value)
            is Right -> Json.stringify(rightSerializer, e.value)
        }

        fun <L, R> parse(
            leftSerializer: KSerializer<L>,
            rightSerializer: KSerializer<R>,
            json: String
        ): Either<L, R> = try {
            Left(Json.parse(leftSerializer, json))
        } catch (_: Throwable) {
            Right(Json.parse(rightSerializer, json))
        }
    }
}