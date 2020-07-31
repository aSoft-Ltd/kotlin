package tz.co.asoft

import kotlinx.serialization.KSerializer

actual class FirestoreDocumentReference

actual val FirestoreDocumentReference.firestore: FirebaseFirestore
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
actual val FirestoreDocumentReference.id: String
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

actual fun FirestoreDocumentReference.collection(path: String): FirestoreCollectionReference {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual suspend fun <T> FirestoreDocumentReference.set(
    data: T,
    serializer: KSerializer<T>,
    then: suspend () -> Unit
) {
}

actual suspend fun FirestoreDocumentReference.fetch(): FirestoreDocumentSnapshot {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual suspend fun <T : Any> FirestoreDocumentReference.put(
    data: T,
    serializer: KSerializer<T>
) {
}

actual fun FirestoreDocumentReference.addListener(listener: (FirestoreDocumentSnapshot) -> Unit) {
}