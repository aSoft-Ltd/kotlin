package tz.co.asoft

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

sealed class Either<out L, out R> {
    @Serializable
    class Left<L, R>(val value: L) : Either<L, R>()

    @Serializable
    class Right<L, R>(val value: R) : Either<L, R>()

    companion object {
        fun <L, R> stringify(
            leftSerializer: KSerializer<L>,
            rightSerializer: KSerializer<R>,
            e: Either<L, R>
        ) = when (e) {
            is Left -> Json.stringify(Left.serializer(leftSerializer, rightSerializer), e)
            is Right -> Json.stringify(Right.serializer(leftSerializer, rightSerializer), e)
        }

        fun <L, R> parse(
            leftSerializer: KSerializer<L>,
            rightSerializer: KSerializer<R>,
            json: String
        ): Either<L, R> = try {
            Json.parse(Left.serializer(leftSerializer, rightSerializer), json)
        } catch (_: Throwable) {
            Json.parse(Right.serializer(leftSerializer, rightSerializer), json)
        }
    }
}