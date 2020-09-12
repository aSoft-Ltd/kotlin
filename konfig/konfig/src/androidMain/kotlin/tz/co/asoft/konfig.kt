package tz.co.asoft

import android.content.Context
import kotlinx.serialization.json.JsonObject

fun Context.konfig(): Map<String, Any> {
    val file = assets.open("konfig.json").bufferedReader()
    return file.readText().toJsonObject().toMap()
}