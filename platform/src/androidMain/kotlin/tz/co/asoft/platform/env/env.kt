package tz.co.asoft.platform.env

import android.content.Context
import kotlinx.serialization.json.JsonObject

fun Context.env() = getEnv().toMap()

fun Context.getEnv(): JsonObject {
    val file = assets.open("platform.environment.json").bufferedReader()
    return file.readText().toJsonObject()
}