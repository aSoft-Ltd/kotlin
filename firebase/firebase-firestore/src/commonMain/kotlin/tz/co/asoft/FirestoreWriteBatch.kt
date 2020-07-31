package tz.co.asoft

import kotlinx.serialization.KSerializer

expect class FirestoreWriteBatch {
    fun delete(documentReference: FirestoreDocumentReference): FirestoreWriteBatch
}

expect fun <T : Any> FirestoreWriteBatch.put(
    documentReference: FirestoreDocumentReference,
    data: T,
    serializer: KSerializer<T>
): FirestoreWriteBatch

expect suspend fun FirestoreWriteBatch.submit()