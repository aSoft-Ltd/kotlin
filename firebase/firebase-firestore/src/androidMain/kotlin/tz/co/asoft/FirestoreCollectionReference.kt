package tz.co.asoft

import kotlinx.serialization.KSerializer

actual typealias FirestoreCollectionReference = com.google.firebase.firestore.CollectionReference

actual val FirestoreCollectionReference.firestore: FirebaseFirestore
    get() = firestore

actual val FirestoreCollectionReference.id: String
    get() = id

actual val FirestoreCollectionReference.parent: FirestoreDocumentReference?
    get() = parent

actual val FirestoreCollectionReference.path: String
    get() = path

actual fun FirestoreCollectionReference.doc(documentPath: String?): FirestoreDocumentReference {
    return if (documentPath != null) {
        document(documentPath)
    } else {
        document()
    }
}

actual suspend fun <T : Any> FirestoreCollectionReference.put(
    data: T,
    serializer: KSerializer<T>
) = add(Json.stringify(serializer, data).toJsonObject().toMap()).await()

actual fun FirestoreCollectionReference.addListener(listener: (FirestoreQuerySnapshot) -> Unit) {
    addSnapshotListener { qs, _ -> qs?.let { listener(it) } }
}