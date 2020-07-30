@file:JvmName("DocumentReference")

package tz.co.asoft.firebase.firestore

import kotlinx.serialization.KSerializer

actual typealias DocumentReference = com.google.firebase.firestore.DocumentReference

actual val DocumentReference.firestore: FirebaseFirestore
    get() = firestore

actual val DocumentReference.id: String
    get() = id

actual fun DocumentReference.collection(path: String): CollectionReference = collection(path)

actual suspend fun <T> DocumentReference.set(data: T, serializer: KSerializer<T>, then: suspend () -> Unit) {
    set(data as Any).await()
    then()
}

actual suspend fun DocumentReference.fetch(): DocumentSnapshot = get().await()

actual suspend fun <T : Any> DocumentReference.put(data: T, serializer: KSerializer<T>) {
    set(data).await()
}

actual fun DocumentReference.addListener(listener: (DocumentSnapshot) -> Unit) {
    addSnapshotListener { doc, exp ->
        doc?.let { listener(it) }
    }
}
