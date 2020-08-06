package tz.co.asoft

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

val Json by lazy { Json(JsonConfiguration(ignoreUnknownKeys = true, isLenient = true)) }