package tz.co.asoft.platform.env

import kotlinx.serialization.json.JsonObject
import tz.co.asoft.platform.core.Ctx

actual fun Ctx.env() = getEnv().toMap()

fun Ctx.getEnv(): JsonObject {
    val file = assets.open("platform.environment.json").bufferedReader()
    return file.readText().toJsonObject()
}