package tz.co.asoft

import kotlinx.serialization.Serializable

@Serializable
data class FileRef(
    val name: String,
    val url: String,
    val permits: List<String> = listOf(),
    val metadata: Map<String, String> = mapOf(),
    val created: Long = DateTime.nowUnixLong(),
    val modified: List<Long> = listOf()
)

fun FileRef.extension() = name.extension()

fun FileRef.extensionOrNull() = name.extensionOrNull()

fun FileRef.nameWithoutExtension() = name.nameWithoutExtension()