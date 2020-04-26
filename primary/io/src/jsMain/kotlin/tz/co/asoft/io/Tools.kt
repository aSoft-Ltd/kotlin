package tz.co.asoft.io

import kotlinx.coroutines.suspendCancellableCoroutine
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Uint8Array
import org.khronos.webgl.get
import org.w3c.files.Blob
import org.w3c.files.FileReader
import kotlin.coroutines.resume

inline fun Uint8Array.toByteArray() = ByteArray(length) { this[it] }

suspend fun Blob.readBytes(onProgress: (Int) -> Unit = {}): ByteArray = suspendCancellableCoroutine { cont ->
    FileReader().apply {
        onprogress = { onProgress(((100.0 * it.loaded.toDouble()) / it.total.toDouble()).toInt()) }
        onload = { cont.resume(Uint8Array(result.unsafeCast<ArrayBuffer>()).toByteArray()) }
        readAsArrayBuffer(this@readBytes)
    }
}