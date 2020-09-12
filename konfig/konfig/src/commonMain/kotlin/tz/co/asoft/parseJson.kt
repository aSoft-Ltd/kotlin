package tz.co.asoft

import kotlinx.serialization.json.*

fun String.toJsonObject(): JsonObject = KJson.parse(JsonObjectSerializer, this)

fun JsonElement.toKObject(): Any? = when (this) {
    is JsonLiteral -> body
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

fun Map<String, Any?>.getMap(key: String) = this[key] as Map<String, Any?>

fun Map<String, Any?>.getList(key: String) = this[key] as List<Any?>