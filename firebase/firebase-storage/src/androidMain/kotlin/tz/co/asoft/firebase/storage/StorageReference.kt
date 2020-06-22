package tz.co.asoft.firebase.storage

import android.net.Uri
import tz.co.asoft.firebase.core.tools.await
import tz.co.asoft.io.File

actual typealias StorageReference = com.google.firebase.storage.StorageReference

actual val StorageReference.root: StorageReference get() = root

actual fun StorageReference.child(path: String): StorageReference = child(path)

actual fun StorageReference.upload(file: File): UploadTask = putFile(Uri.fromFile(file))

actual suspend fun StorageReference.downloadUrl(): String? = downloadUrl.await().toString()

actual suspend fun StorageReference.remove() {
    delete().await()
}