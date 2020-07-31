package tz.co.asoft

import kotlinx.coroutines.await
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlin.js.Promise

actual external class FirestoreDocumentReference {
    val firestore: FirebaseFirestore
    val id: String
    fun collection(path: String): FirestoreCollectionReference
    fun get(): Promise<FirestoreDocumentSnapshot>
    fun set(obj: Any): Promise<Unit>
    fun update(data: Any): Promise<Unit>
    fun onSnapshot(l: Listener<FirestoreDocumentSnapshot>)
}

actual val FirestoreDocumentReference.firestore: FirebaseFirestore
    get() = firestore


actual val FirestoreDocumentReference.id: String
    get() = id

actual fun FirestoreDocumentReference.collection(path: String): FirestoreCollectionReference =
    collection(path)

actual suspend fun <T> FirestoreDocumentReference.set(
    data: T,
    serializer: KSerializer<T>,
    then: suspend () -> Unit
) {
    val obj = JSON.parse<Any>(Json.stringify(serializer, data))
    set(obj).await()
    then()
}

actual suspend fun FirestoreDocumentReference.fetch(): FirestoreDocumentSnapshot = get().await()

actual suspend fun <T : Any> FirestoreDocumentReference.put(data: T, serializer: KSerializer<T>) {
    val obj = JSON.parse<Any>(Json.stringify(serializer, data))
    set(obj).await()
}

actual fun FirestoreDocumentReference.addListener(listener: (FirestoreDocumentSnapshot) -> Unit) {
    onSnapshot(Listener(
        next = { listener(it) },
        error = {}
    ))
}