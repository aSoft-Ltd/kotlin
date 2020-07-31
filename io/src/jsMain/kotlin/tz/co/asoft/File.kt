package tz.co.asoft

import org.w3c.files.Blob

actual typealias File = org.w3c.files.File

actual val File.name: String
    get() = name

actual suspend fun File.readBytes(): ByteArray = (this as Blob).readBytes(null)
