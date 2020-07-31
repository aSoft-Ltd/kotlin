@file:JvmName("DocumentReference")

package tz.co.asoft

import kotlinx.serialization.KSerializer

actual typealias FirestoreDocumentReference = com.google.firebase.firestore.DocumentReference

actual val FirestoreDocumentReference.firestore: FirebaseFirestore
    get() = firestore

actual val FirestoreDocumentReference.id: String
    get() = id

actual fun FirestoreDocumentReference.collection(path: String): FirestoreCollectionReference = collection(path)

actual suspend fun <T> FirestoreDocumentReference.set(data: T, serializer: KSerializer<T>, then: suspend () -> Unit) {
    set(data as Any).await()
    then()
}

actual suspend fun FirestoreDocumentReference.fetch(): FirestoreDocumentSnapshot = get().await()

actual suspend fun <T : Any> FirestoreDocumentReference.put(data: T, serializer: KSerializer<T>) {
    set(data).await()
}

actual fun FirestoreDocumentReference.addListener(listener: (FirestoreDocumentSnapshot) -> Unit) {
    addSnapshotListener { doc, exp ->
        doc?.let { listener(it) }
    }
}
