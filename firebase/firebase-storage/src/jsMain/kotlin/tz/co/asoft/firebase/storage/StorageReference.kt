package tz.co.asoft.firebase.storage

import kotlinx.coroutines.await
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Uint8Array
import org.w3c.files.Blob
import tz.co.asoft.io.File
import kotlin.js.Promise

actual external class StorageReference {
    val root: StorageReference
    fun getDownloadURL(): Promise<String?>
    fun delete(): Promise<Any?>
    fun child(path: String?): StorageReference
    fun put(blob: Blob): UploadTask
    fun put(uint8Array: Uint8Array): UploadTask
    fun put(buffer: ArrayBuffer): UploadTask
}

actual val StorageReference.root: StorageReference get() = root

actual fun StorageReference.child(path: String): StorageReference = child(path)

actual fun StorageReference.upload(file: File): UploadTask = put(file)

actual suspend fun StorageReference.downloadUrl() = getDownloadURL().await()

actual suspend fun StorageReference.remove() {
    delete().await()
}