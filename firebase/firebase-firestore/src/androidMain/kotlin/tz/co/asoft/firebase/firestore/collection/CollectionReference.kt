package tz.co.asoft.firebase.firestore.collection

import tz.co.asoft.firebase.firestore.FirebaseFirestore
import tz.co.asoft.firebase.firestore.document.DocumentReference
import tz.co.asoft.firebase.firestore.snapshot.QueryDocumentSnapshot
import tz.co.asoft.firebase.firestore.tools.await
import kotlinx.serialization.KSerializer
import tz.co.asoft.firebase.firestore.serializer.json
import tz.co.asoft.firebase.firestore.snapshot.QuerySnapshot
import tz.co.asoft.platform.env.toJsonObject
import tz.co.asoft.platform.env.toMap

actual typealias CollectionReference = com.google.firebase.firestore.CollectionReference

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
        document(documentPath)
    } else {
        document()
    }
}

actual suspend fun CollectionReference.forEachAsync(action: (QueryDocumentSnapshot) -> Unit): Unit {
    get().await().forEach(action)
}

actual suspend fun <T : Any> CollectionReference.put(
    data: T,
    serializer: KSerializer<T>
): DocumentReference {
    val map = json.stringify(serializer, data).toJsonObject().toMap()
    return add(map).await()
}

actual fun CollectionReference.addListener(listener: (QuerySnapshot) -> Unit) {
    addSnapshotListener { qs, _ -> qs?.let { listener(it) } }
}