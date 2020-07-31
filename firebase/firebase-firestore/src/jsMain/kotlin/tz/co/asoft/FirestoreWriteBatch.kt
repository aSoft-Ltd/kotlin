package tz.co.asoft

import kotlinx.coroutines.await
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlin.js.Promise

actual external class FirestoreWriteBatch {
    fun commit(): Promise<Unit>

    actual fun delete(documentReference: FirestoreDocumentReference): FirestoreWriteBatch

    fun set(documentReference: FirestoreDocumentReference, data: Any): FirestoreWriteBatch
}

actual fun <T : Any> FirestoreWriteBatch.put(
    documentReference: FirestoreDocumentReference,
    data: T,
    serializer: KSerializer<T>
): FirestoreWriteBatch {
    val obj = JSON.parse<Any>(Json.stringify(serializer, data))
    return set(documentReference, obj)
}

actual suspend fun FirestoreWriteBatch.submit() = commit().await()