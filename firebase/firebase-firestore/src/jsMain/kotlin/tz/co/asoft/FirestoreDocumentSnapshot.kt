package tz.co.asoft

actual open external class FirestoreDocumentSnapshot {
    val id: String
    fun data(): Map<String, Any>?
    fun get(fieldPath: String): Any?
}

actual fun FirestoreDocumentSnapshot.toJson(): String? = data()?.let { JSON.stringify(it) }

actual fun FirestoreDocumentSnapshot.get(fieldPath: String): Any? = get(fieldPath)
actual fun FirestoreDocumentSnapshot.data(): Map<String, Any>? = data()
actual val FirestoreDocumentSnapshot.id: String
    get() = id