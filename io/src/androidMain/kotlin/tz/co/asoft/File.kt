@file:JvmName("FileAndroid")

package tz.co.asoft

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

actual typealias File = java.io.File

actual val File.name: String
    get() = name

actual suspend fun File.readBytes(): ByteArray = withContext(Dispatchers.IO) {
    inputStream().readBytes()
}
