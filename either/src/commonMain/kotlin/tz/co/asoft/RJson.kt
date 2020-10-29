package tz.co.asoft

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

val EJson by lazy { Json(JsonConfiguration(ignoreUnknownKeys = true, prettyPrint = true)) }