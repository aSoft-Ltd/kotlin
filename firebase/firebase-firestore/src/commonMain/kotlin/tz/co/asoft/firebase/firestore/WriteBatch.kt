package tz.co.asoft.firebase.firestore

import kotlinx.serialization.KSerializer

expect class WriteBatch {
    fun delete(documentReference: DocumentReference): WriteBatch
}

expect fun <T : Any> WriteBatch.put(
    documentReference: DocumentReference,
    data: T,
    serializer: KSerializer<T>
): WriteBatch

expect suspend fun WriteBatch.submit()