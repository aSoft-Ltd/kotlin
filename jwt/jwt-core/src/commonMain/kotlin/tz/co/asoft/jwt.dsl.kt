@file:Suppress("PackageDirectoryMismatch")

package tz.co.asoft

@OptIn(ExperimentalStdlibApi::class)
fun JWT.token(): String {
    val headerHex = header.toJson().toByteArray().base64.replace("=", "")
    val payloadHex = payload.toJson().toByteArray().base64.replace("=", "")
    return "$headerHex.$payloadHex" + (signature?.let { ".$it" } ?: "")
}