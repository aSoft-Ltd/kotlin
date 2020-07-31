package tz.co.asoft

import kotlinx.serialization.KSerializer

actual typealias FirestoreWriteBatch = com.google.firebase.firestore.WriteBatch

actual fun <T : Any> FirestoreWriteBatch.put(
    documentReference: FirestoreDocumentReference,
    data: T,
    serializer: KSerializer<T>
): FirestoreWriteBatch = set(documentReference, data)

actual suspend fun FirestoreWriteBatch.submit() {
    commit().await()
}