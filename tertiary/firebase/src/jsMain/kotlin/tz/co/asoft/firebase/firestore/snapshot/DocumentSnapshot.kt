package tz.co.asoft.firebase.firestore.snapshot

actual open external class DocumentSnapshot {
    val id: String
    fun data(): Map<String, Any>?
    fun get(fieldPath: String): Any?
}

actual fun DocumentSnapshot.toJson(): String? = data()?.let { JSON.stringify(it) }

actual fun DocumentSnapshot.get(fieldPath: String): Any? = get(fieldPath)
actual fun DocumentSnapshot.data(): Map<String, Any>? = data()
actual val DocumentSnapshot.id: String
    get() = id