package tz.co.asoft.firebase.firestore

import kotlinx.coroutines.await
import kotlinx.serialization.KSerializer
import kotlin.js.Promise

actual external class CollectionReference : Query {
    val firestore: FirebaseFirestore
    val id: String
    val parent: DocumentReference?
    val path: String

    @JsName("doc")
    fun doc(path: String? = definedExternally): DocumentReference

    fun add(obj: Any): Promise<DocumentReference>

    fun onSnapshot(l: Listener<QuerySnapshot>)
}

actual val CollectionReference.firestore: FirebaseFirestore
    get() = firestore

actual val CollectionReference.id: String
    get() = id

actual val CollectionReference.parent: DocumentReference?
    get() = parent

actual val CollectionReference.path: String
    get() = path

actual fun CollectionReference.doc(documentPath: String?): DocumentReference {
    return if (documentPath != null) {
        doc(documentPath)
    } else {
        doc()
    }
}

actual suspend fun <T : Any> CollectionReference.put(
    data: T,
    serializer: KSerializer<T>
): DocumentReference {
    val jsonString = json.stringify(serializer, data)
    return add(JSON.parse(jsonString)).await()
}

actual fun CollectionReference.addListener(listener: (QuerySnapshot) -> Unit) {
    onSnapshot(
        Listener(
            next = { listener(it) },
            error = {}
        )
    )
}