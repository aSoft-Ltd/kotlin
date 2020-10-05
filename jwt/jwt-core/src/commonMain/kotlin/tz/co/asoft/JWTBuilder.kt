@file:Suppress("PackageDirectoryMismatch")

package tz.co.asoft

data class JWTBuilder(
    var header: JWTHeader = mutableMapOf("typ" to "JWT"),
    var payload: JWTPayload = mutableMapOf()
) {
    fun build() = JWT(header, payload)
}