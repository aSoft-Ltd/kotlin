@file:JvmName("StorageReferenceCommon")

package tz.co.asoft

import kotlin.jvm.JvmName

expect class FirebaseStorageReference

expect val FirebaseStorageReference.root: FirebaseStorageReference
expect fun FirebaseStorageReference.child(path: String): FirebaseStorageReference

expect fun FirebaseStorageReference.upload(file: File): FirebaseStorageUploadTask

suspend fun FirebaseStorageReference.push(file: File): FileRef {
    upload(file).await()
    return FileRef(
        name = file.name,
        url = downloadUrl() ?: throw Exception("Failed to get download url of ${file.name}")
    )
}

expect suspend fun FirebaseStorageReference.downloadUrl(): String?
expect suspend fun FirebaseStorageReference.remove()