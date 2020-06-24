package tz.co.asoft.firebase.storage

import tz.co.asoft.io.File
import tz.co.asoft.io.FileRef
import tz.co.asoft.io.name

expect class StorageReference

expect val StorageReference.root: StorageReference
expect fun StorageReference.child(path: String): StorageReference

expect fun StorageReference.upload(file: File): UploadTask

suspend fun StorageReference.push(file: File): FileRef {
    upload(file).await()
    return FileRef(
        name = file.name,
        url = downloadUrl() ?: throw Exception("Failed to get download url of ${file.name}")
    )
}

expect suspend fun StorageReference.downloadUrl(): String?
expect suspend fun StorageReference.remove()