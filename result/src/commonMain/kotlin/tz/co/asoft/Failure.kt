package tz.co.asoft

import kotlinx.serialization.Serializable

@Serializable
class Failure(
    val error: String,
    val type: String,
    val reason: String? = null,
    val stackTrace: String? = null
) {
    override fun toString() = "Failure($error)"
}