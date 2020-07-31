package tz.co.asoft

actual external class FirestoreQuerySnapshot {
    val docs: Array<FirestoreQueryDocumentSnapshot>
    fun forEach(callback: (FirestoreQueryDocumentSnapshot) -> Unit)
}

actual val FirestoreQuerySnapshot.documents: List<FirestoreQueryDocumentSnapshot> get() = docs.toList()

actual fun FirestoreQuerySnapshot.forEach(callback: (FirestoreQueryDocumentSnapshot) -> Unit) = forEach(callback)
