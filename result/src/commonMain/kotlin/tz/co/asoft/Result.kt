package tz.co.asoft

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed class Result<out T> {
    @Serializable
    class Success<out T>(val data: T) : Result<T>()

    @Serializable
    class Failure<out T>(
        @SerialName("error") val msg: String,
        val type: String,
        val reason: String? = null,
        val stackTrace: String? = null
    ) : Result<T>()

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
                Failure<T>(
                    msg = c.message ?: c.cause?.message ?: "Failed to serialize json",
                    type = c::class.simpleName ?: "Unknown Type",
                    reason = c.cause?.message,
                    stackTrace = c.cause?.cause?.message
                )
            }
        }
    }
}