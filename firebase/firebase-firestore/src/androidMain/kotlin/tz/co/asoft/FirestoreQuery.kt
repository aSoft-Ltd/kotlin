@file:JvmName("AndroidQuery")

package tz.co.asoft

actual typealias FirestoreQuery = com.google.firebase.firestore.Query

actual suspend fun FirestoreQuery.fetch(): FirestoreQuerySnapshot = get().await()

actual suspend fun FirestoreQuery.get(then: suspend (FirestoreQuerySnapshot) -> Unit) {
    then(get().await())
}

actual fun FirestoreQuery.limit(limit: Int): FirestoreQuery = limit(limit)

actual fun FirestoreQuery.start(at: FirestoreDocumentSnapshot) = startAt(at)

actual fun FirestoreQuery.where(fieldPath: String, operator: String, value: Any): FirestoreQuery = when (operator) {
    "<" -> whereLessThan(fieldPath, value)
    "<=" -> whereLessThanOrEqualTo(fieldPath, value)
    "==" -> whereEqualTo(fieldPath, value)
    ">=" -> whereGreaterThanOrEqualTo(fieldPath, value)
    ">" -> whereGreaterThan(fieldPath, value)
    "array-contains" -> whereArrayContains(fieldPath, value)
    else -> this
}

actual fun FirestoreQuery.orderedBy(fieldPath: String): FirestoreQuery = orderBy(fieldPath)