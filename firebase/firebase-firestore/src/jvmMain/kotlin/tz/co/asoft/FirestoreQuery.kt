package tz.co.asoft

actual open class FirestoreQuery

actual fun FirestoreQuery.where(
    fieldPath: String,
    operator: String,
    value: Any
): FirestoreQuery {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun FirestoreQuery.limit(limit: Int): FirestoreQuery {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual suspend fun FirestoreQuery.get(then: suspend (FirestoreQuerySnapshot) -> Unit) {
}

actual suspend fun FirestoreQuery.fetch(): FirestoreQuerySnapshot = TODO()
actual fun FirestoreQuery.start(at: FirestoreDocumentSnapshot): FirestoreQuery {
    TODO("Not yet implemented")
}

actual fun FirestoreQuery.orderedBy(fieldPath: String): FirestoreQuery {
    TODO("Not yet implemented")
}