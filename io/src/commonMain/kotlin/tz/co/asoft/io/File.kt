@file:JvmName("FileCommon")
package tz.co.asoft.io

import kotlin.jvm.JvmName

expect class File

expect val File.name: String

expect suspend fun File.readBytes(): ByteArray

//expect suspend fun File.readBytes(onProgress: (Int) -> Unit): ByteArray

val File.ext get() = name.split(".").lastOrNull()
val File.nameWithoutExtension: String get() = (name.split(".") - ext).joinToString(".")