package tz.co.asoft

import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

actual external class FirebaseStorageUploadTask {
    fun on(
        event: String,
        progress: ((TaskSnapshot) -> Unit)?,
        error: ((dynamic) -> Unit)?,
        complete: (() -> Unit)?
    )
}

actual suspend fun FirebaseStorageUploadTask.await() = suspendCancellableCoroutine<Unit> { cont ->
    on(
        event = "state_changed",
        progress = null,
        error = {
            cont.resumeWithException(Throwable("Failed to upload file: $it"))
        },
        complete = {
            cont.resume(Unit)
        }
    )
}

actual fun FirebaseStorageUploadTask.onProgress(call: (FirebaseStorageUploadTaskSnapshot) -> Unit) = on(
    event = "state_changed",
    progress = {
        call(JSTaskSnapshot(it))
    },
    error = null,
    complete = null
)