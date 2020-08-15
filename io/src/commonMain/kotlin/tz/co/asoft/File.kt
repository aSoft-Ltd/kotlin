@file:JvmName("FileCommon")

package tz.co.asoft

import kotlin.jvm.JvmName

expect class File

expect val File.name: String

expect suspend fun File.readBytes(): ByteArray

fun File.extension() = name.extension()

fun File.extensionOrNull() = name.extensionOrNull()

fun File.nameWithoutExtension() = name.nameWithoutExtension()