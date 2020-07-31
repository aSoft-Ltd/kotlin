package tz.co.asoft

import kotlinx.serialization.KSerializer

actual class FirestoreWriteBatch {
    actual fun delete(documentReference: FirestoreDocumentReference): FirestoreWriteBatch {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

actual fun <T : Any> FirestoreWriteBatch.put(
    documentReference: FirestoreDocumentReference,
    data: T,
    serializer: KSerializer<T>
): FirestoreWriteBatch {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual suspend fun FirestoreWriteBatch.submit() {
}