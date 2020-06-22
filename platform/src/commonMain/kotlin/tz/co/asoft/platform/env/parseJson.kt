package tz.co.asoft.platform.env

import kotlinx.serialization.json.*

fun String.toJsonObject(): JsonObject = Json.parse(JsonObjectSerializer, this)


fun JsonElement.toKObject(): Any? = when (this) {
    is JsonLiteral -> content
    JsonNull -> null
    is JsonObject -> content.mapValues { it.value.toKObject() }
    is JsonArray -> content.map { it.toKObject() }
}

fun JsonObject.toMap(): Map<String, Any> {
    val map = mutableMapOf<String, Any>()
    for ((k, v) in content) {
        v.toKObject()?.let { map[k] = it }
    }
    return map
}

fun Map<String, *>.getMap(key: String) = this[key] as Map<String, *>

fun Map<String, *>.getList(key: String) = this[key] as List<*>