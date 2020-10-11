@file:Suppress("PackageDirectoryMismatch")

package tz.co.asoft

@OptIn(ExperimentalStdlibApi::class)
fun JWT.token() = "$headerInBase64.$payloadInBase64" + (signature?.let { ".$it" } ?: "")

val JWT.headerInBase64 get() = header.toJson().toByteArray().base64Url

val JWT.payloadInBase64 get() = payload.toJson().toByteArray().base64Url

val JWT.message get() = "$headerInBase64.$payloadInBase64".toByteArray()