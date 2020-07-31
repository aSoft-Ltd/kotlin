package tz.co.asoft

import kotlinx.coroutines.await
import kotlinx.serialization.KSerializer
import kotlin.js.Promise

actual external class FirestoreCollectionReference : FirestoreQuery {
    val firestore: FirebaseFirestore
    val id: String
    val parent: FirestoreDocumentReference?
    val path: String

    @JsName("doc")
    fun doc(path: String? = definedExternally): FirestoreDocumentReference

    fun add(obj: Any): Promise<FirestoreDocumentReference>

    fun onSnapshot(l: Listener<FirestoreQuerySnapshot>)
}

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
        doc(documentPath)
    } else {
        doc()
    }
}

actual suspend fun <T : Any> FirestoreCollectionReference.put(
    data: T,
    serializer: KSerializer<T>
): FirestoreDocumentReference {
    val jsonString = Json.stringify(serializer, data)
    return add(JSON.parse(jsonString)).await()
}

actual fun FirestoreCollectionReference.addListener(listener: (FirestoreQuerySnapshot) -> Unit) {
    onSnapshot(
        Listener(
            next = { listener(it) },
            error = {}
        )
    )
}