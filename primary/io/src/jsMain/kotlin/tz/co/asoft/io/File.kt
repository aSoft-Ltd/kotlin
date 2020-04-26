package tz.co.asoft.io

import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Uint8Array
import org.w3c.files.Blob
import org.w3c.files.File as JsFile

actual class File private actual constructor(actual val ref: Any, actual var name: String) {

    actual constructor(byteArray: ByteArray, name: String) : this(byteArray as Any, name)

    constructor(blob: Blob, name: String = "tmp") : this(blob as Any, name)

    constructor(file: JsFile, name: String = file.name) : this(file as Any, name)

    constructor(uInt8Array: Uint8Array, name: String = "tmp") : this(uInt8Array as Any, name)

    constructor(arrayBuffer: ArrayBuffer, name: String = "tmp") : this(arrayBuffer as Any, name)

    actual suspend fun readBytes() = readBytes { }

    actual suspend fun readBytes(onProgress: (Int) -> Unit): ByteArray = when (val f = ref) {
        is ByteArray -> f
        is Uint8Array -> f.toByteArray()
        is ArrayBuffer -> Uint8Array(f).toByteArray()
        is Blob -> f.readBytes(onProgress)
        is JsFile -> f.readBytes(onProgress)
        else -> throw IOException("Can't read bytes")
    }
}