@file:JvmName("FileCommon")

package tz.co.asoft.io

import kotlin.jvm.JvmName

expect class File

expect val File.name: String

expect suspend fun File.readBytes(): ByteArray

@Deprecated(
    message = "Use extension()/extensionOrNull() method",
    replaceWith = ReplaceWith("extensionOrNull()")
)
val File.ext
    get() = name.split(".").lastOrNull()

fun File.extension() = name.extension()
fun File.extensionOrNull() = name.extensionOrNull()

@Deprecated(
    message = "Use the method",
    replaceWith = ReplaceWith("nameWithoutExtension()")
)
val File.nameWithoutExtension: String
    get() = (name.split(".") - ext).joinToString(".")

fun File.nameWithoutExtension() = name.nameWithoutExtension()