@file:JvmName("FileJvm")

package tz.co.asoft.io

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

actual typealias File = java.io.File

actual val File.name: String
    get() = name

actual suspend fun File.readBytes(): ByteArray = withContext(Dispatchers.IO) {
    inputStream().readBytes()
}

//actual suspend fun File.readBytes(onProgress: (Int) -> Unit): ByteArray = readBytes()

//actual class File private actual constructor(actual val ref: Any, actual var name: String) {
//
//    actual constructor(byteArray: ByteArray, name: String) : this(byteArray as Any, name)
//
//    constructor(inputStream: InputStream, name: String = "tmp") : this(inputStream as Any, name)
//
//    constructor(file: JavaFile, name: String = file.name) : this(file as Any, name)
//
//    constructor(uri: URI, name: String = JavaFile(uri).name) : this(uri as Any, name)
//
//    constructor(path: String) : this(JavaFile(path))
//
//    actual suspend fun readBytes() = readBytes { }
//
//    actual suspend fun readBytes(onProgress: (Int) -> Unit): ByteArray = withContext(Dispatchers.IO) {
//        when (val r = ref) {
//            is ByteArray -> r
//            is JavaFile -> r.readBytes()
//            is URI -> JavaFile(r).readBytes()
//            is InputStream -> r.readBytes()
//            else -> throw IOException("Can't read bytes")
//        }
//    }
//
//    val javaRef
//        get() : JavaFile? = when (val f = ref) {
//            is JavaFile -> f
//            is URI -> JavaFile(f)
//            else -> null
//        }
//}