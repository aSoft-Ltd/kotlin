package tz.co.asoft

actual typealias FirebaseStorage = com.google.firebase.storage.FirebaseStorage

actual val FirebaseStorage.app: FirebaseApp get() = app

actual fun FirebaseStorage.ref(path: String?): FirebaseStorageReference = if (path != null) {
    getReference(path)
} else {
    reference
}

actual fun FirebaseApp.storage(): FirebaseStorage = FirebaseStorage.getInstance(this)