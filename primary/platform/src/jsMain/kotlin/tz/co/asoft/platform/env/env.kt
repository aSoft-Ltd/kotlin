package tz.co.asoft.platform.env

import kotlinx.serialization.json.JsonObject
import tz.co.asoft.platform.core.Ctx

actual fun Ctx.env() = getEnv()

fun env() = getEnv()

private fun getEnv() = platformEnvironment.toJsonObject().toMap()

private fun <T> T.toJsonObject(): JsonObject = JSON.stringify(this).toJsonObject()

@JsModule("platform.environment.json")
@JsNonModule
internal external val platformEnvironment: Any