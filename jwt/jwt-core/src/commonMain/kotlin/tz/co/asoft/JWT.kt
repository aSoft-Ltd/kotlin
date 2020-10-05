@file:Suppress("PackageDirectoryMismatch")

package tz.co.asoft

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class JWT(
    val header: JWTHeader,
    val payload: JWTPayload,
    val signature: String? = null
)