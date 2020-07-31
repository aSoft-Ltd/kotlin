package tz.co.asoft

actual class FirebaseStorageUploadTask

actual fun FirebaseStorageUploadTask.onProgress(call: (FirebaseStorageUploadTaskSnapshot) -> Unit) {
}

actual suspend fun FirebaseStorageUploadTask.await() {
}