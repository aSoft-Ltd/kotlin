package tz.co.asoft.firebase.firestore

import kotlinx.serialization.KSerializer

actual typealias WriteBatch = com.google.firebase.firestore.WriteBatch

actual fun <T : Any> WriteBatch.put(
    documentReference: DocumentReference,
    data: T,
    serializer: KSerializer<T>
): WriteBatch = set(documentReference, data)

actual suspend fun WriteBatch.submit() {
    commit().await()
}