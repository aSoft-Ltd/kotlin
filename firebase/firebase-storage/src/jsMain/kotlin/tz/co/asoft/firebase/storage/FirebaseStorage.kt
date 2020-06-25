package tz.co.asoft.firebase.storage

import tz.co.asoft.firebase.core.FirebaseApp

@JsModule("firebase/storage")
private external val storageLib: dynamic

actual external class FirebaseStorage {
    val app: FirebaseApp
    fun ref(path: String?): StorageReference
}

actual fun FirebaseApp.storage(): FirebaseStorage {
    if (storageLib.isImported != true) {
        storageLib.isImported = true
    }
    return this.unsafeCast<dynamic>().storage()
}

actual val FirebaseStorage.app: FirebaseApp get() = app
actual fun FirebaseStorage.ref(path: String?): StorageReference = ref(path)