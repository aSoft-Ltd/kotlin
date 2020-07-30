package tz.co.asoft.firebase.firestore

import kotlinx.serialization.KSerializer
import tz.co.asoft.toJsonObject

actual typealias CollectionReference = com.google.firebase.firestore.CollectionReference

actual val CollectionReference.firestore: FirebaseFirestore
    get() = firestore

actual val CollectionReference.id: String
    get() = id

actual val CollectionReference.parent: DocumentReference?
    get() = parent

actual val CollectionReference.path: String
    get() = path

actual fun CollectionReference.doc(documentPath: String?): DocumentReference {
    return if (documentPath != null) {
        document(documentPath)
    } else {
        document()
    }
}

actual suspend fun <T : Any> CollectionReference.put(
    data: T,
    serializer: KSerializer<T>
) = add(json.stringify(serializer, data).toJsonObject().toMap()).await()

actual fun CollectionReference.addListener(listener: (QuerySnapshot) -> Unit) {
    addSnapshotListener { qs, _ -> qs?.let { listener(it) } }
}