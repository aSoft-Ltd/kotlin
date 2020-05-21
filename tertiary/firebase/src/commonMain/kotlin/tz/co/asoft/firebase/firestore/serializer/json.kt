package tz.co.asoft.firebase.firestore.serializer

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

val json
    get() = Json(
        JsonConfiguration(
            ignoreUnknownKeys = true,
            isLenient = true
        )
    )