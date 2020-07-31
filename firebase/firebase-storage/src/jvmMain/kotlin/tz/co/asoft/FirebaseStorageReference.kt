package tz.co.asoft

actual class FirebaseStorageReference

actual val FirebaseStorageReference.root: FirebaseStorageReference
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

actual fun FirebaseStorageReference.child(path: String): FirebaseStorageReference {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

/**
 * JavaScript Accepts:  Blob, Uint8Array and ArrayBuffer
 * Android Accepts:     ByteArray, android.content.Uri, InputStream
 */
actual fun FirebaseStorageReference.upload(file: File): FirebaseStorageUploadTask {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual suspend fun FirebaseStorageReference.downloadUrl(): String? {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual suspend fun FirebaseStorageReference.remove() {
}