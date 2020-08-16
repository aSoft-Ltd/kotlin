package tz.co.asoft

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

sealed class Result<out T> {
    @Serializable
    class Success<out T>(val data: T) : Result<T>()

    @Serializable
    class Failure<out T>(val msg: String) : Result<T>()

    companion object {
        fun <T> stringify(serializer: KSerializer<T>, res: Result<T>) = when (res) {
            is Success -> RJson.stringify(Success.serializer(serializer), res)
            is Failure -> RJson.stringify(Failure.serializer(serializer), res)
        }

        fun <T> parse(serializer: KSerializer<T>, json: String) = try {
            RJson.parse(Success.serializer(serializer), json)
        } catch (c1: Throwable) {
            try {
                RJson.parse(Failure.serializer(serializer), json)
            } catch (c: Throwable) {
                Failure<T>(c.message ?: c.cause?.message ?: "Failed to serialize json")
            }
        }
    }
}