@file:Suppress("PackageDirectoryMismatch")

package tz.co.asoft

import kotlinx.serialization.Serializable

@Serializable
data class JWTHeader(
    val alg: String,
    val typ: String = "JWT"
)