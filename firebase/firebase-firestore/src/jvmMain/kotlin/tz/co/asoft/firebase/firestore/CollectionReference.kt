package tz.co.asoft.firebase.firestore

import kotlinx.serialization.KSerializer

actual class CollectionReference : Query()

actual val CollectionReference.firestore: FirebaseFirestore
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
actual val CollectionReference.id: String
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
actual val CollectionReference.parent: DocumentReference?
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
actual val CollectionReference.path: String
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

actual fun CollectionReference.doc(documentPath: String?): DocumentReference {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual suspend fun <T : Any> CollectionReference.put(
    data: T,
    serializer: KSerializer<T>
): DocumentReference {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun CollectionReference.addListener(listener: (QuerySnapshot) -> Unit) {
}