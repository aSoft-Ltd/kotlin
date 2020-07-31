package tz.co.asoft

expect class FirebaseStorageUploadTask

expect fun FirebaseStorageUploadTask.onProgress(call: (FirebaseStorageUploadTaskSnapshot)->Unit)

expect suspend fun FirebaseStorageUploadTask.await()