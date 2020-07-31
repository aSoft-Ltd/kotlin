@file:JvmName("QuerySnapshotAndroid")

package tz.co.asoft

actual typealias FirestoreQuerySnapshot = com.google.firebase.firestore.QuerySnapshot

operator fun FirestoreQuerySnapshot.iterator() = iterator()

actual val FirestoreQuerySnapshot.documents: List<FirestoreQueryDocumentSnapshot> get() = documents.map { it as FirestoreQueryDocumentSnapshot }

actual fun FirestoreQuerySnapshot.forEach(callback: (FirestoreQueryDocumentSnapshot) -> Unit) {
    forEach {
        callback(it)
    }
}