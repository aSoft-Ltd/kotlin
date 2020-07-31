package tz.co.asoft

import kotlinx.serialization.KSerializer

actual class FirestoreCollectionReference : FirestoreQuery()

actual val FirestoreCollectionReference.firestore: FirebaseFirestore
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
actual val FirestoreCollectionReference.id: String
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
actual val FirestoreCollectionReference.parent: FirestoreDocumentReference?
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
actual val FirestoreCollectionReference.path: String
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

actual fun FirestoreCollectionReference.doc(documentPath: String?): FirestoreDocumentReference {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual suspend fun <T : Any> FirestoreCollectionReference.put(
    data: T,
    serializer: KSerializer<T>
): FirestoreDocumentReference {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun FirestoreCollectionReference.addListener(listener: (FirestoreQuerySnapshot) -> Unit) {
}