@file:JvmName("StorageReferenceAndroid")

package tz.co.asoft

import android.net.Uri

actual typealias FirebaseStorageReference = com.google.firebase.storage.StorageReference

actual val FirebaseStorageReference.root: FirebaseStorageReference get() = root

actual fun FirebaseStorageReference.child(path: String): FirebaseStorageReference = child(path)

actual fun FirebaseStorageReference.upload(file: File): FirebaseStorageUploadTask = putFile(Uri.fromFile(file))

actual suspend fun FirebaseStorageReference.downloadUrl(): String? = downloadUrl.await().toString()

actual suspend fun FirebaseStorageReference.remove() {
    delete().await()
}