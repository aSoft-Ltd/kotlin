package tz.co.asoft

import kotlinx.serialization.json.JsonObject

fun env() = getEnv()

private fun getEnv() = try {
    require<Map<String, Any>>("platform.environment.json")
} catch (e: Throwable) {
    Unit
}.toJsonObject().toMap()

private fun <T> T.toJsonObject(): JsonObject = JSON.stringify(this).toJsonObject()