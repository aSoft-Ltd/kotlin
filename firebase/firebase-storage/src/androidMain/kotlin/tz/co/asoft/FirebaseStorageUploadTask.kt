package tz.co.asoft

import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

actual typealias FirebaseStorageUploadTask = com.google.firebase.storage.UploadTask

actual suspend fun FirebaseStorageUploadTask.await() = suspendCancellableCoroutine<Unit> { cont ->
    addOnSuccessListener {
        cont.resume(Unit)
    }
    addOnFailureListener {
        cont.resumeWithException(it)
    }
}

actual fun FirebaseStorageUploadTask.onProgress(call: (FirebaseStorageUploadTaskSnapshot) -> Unit) {
    addOnProgressListener {
        call(AndroidUploadTaskSnapshot(it))
    }
}