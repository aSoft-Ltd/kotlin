@file:JvmName("QuerySnapshotAndroid")

package tz.co.asoft

actual class FirestoreQuerySnapshot

actual val FirestoreQuerySnapshot.documents: List<FirestoreQueryDocumentSnapshot>
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

actual fun FirestoreQuerySnapshot.forEach(callback: (FirestoreQueryDocumentSnapshot) -> Unit) {
}