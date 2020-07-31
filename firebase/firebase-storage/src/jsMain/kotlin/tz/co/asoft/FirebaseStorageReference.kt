package tz.co.asoft

import kotlinx.coroutines.await
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Uint8Array
import org.w3c.files.Blob
import kotlin.js.Promise

actual external class FirebaseStorageReference {
    val root: FirebaseStorageReference
    fun getDownloadURL(): Promise<String?>
    fun delete(): Promise<Any?>
    fun child(path: String?): FirebaseStorageReference
    fun put(blob: Blob): FirebaseStorageUploadTask
    fun put(uint8Array: Uint8Array): FirebaseStorageUploadTask
    fun put(buffer: ArrayBuffer): FirebaseStorageUploadTask
}

actual val FirebaseStorageReference.root: FirebaseStorageReference get() = root

actual fun FirebaseStorageReference.child(path: String): FirebaseStorageReference = child(path)

actual fun FirebaseStorageReference.upload(file: File): FirebaseStorageUploadTask = put(file)

actual suspend fun FirebaseStorageReference.downloadUrl() = getDownloadURL().await()

actual suspend fun FirebaseStorageReference.remove() {
    delete().await()
}