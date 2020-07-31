package tz.co.asoft

import kotlinx.coroutines.await
import kotlin.js.Promise

actual open external class FirestoreQuery {
    open fun get(): Promise<FirestoreQuerySnapshot>
    fun where(field: String, opStr: String, value: Any): FirestoreQuery
    fun limit(limit: Number): FirestoreQuery
    fun startAt(snapshot: FirestoreDocumentSnapshot): FirestoreQuery
    fun startAfter(snapshot: FirestoreDocumentSnapshot): FirestoreQuery
    fun endBefore(snapshot: FirestoreDocumentSnapshot): FirestoreQuery
    fun endAt(snapshot: FirestoreDocumentSnapshot): FirestoreQuery
    fun orderBy(field: String): FirestoreQuery
}

actual suspend fun FirestoreQuery.fetch(): FirestoreQuerySnapshot = get().await()

actual suspend fun FirestoreQuery.get(then: suspend (FirestoreQuerySnapshot) -> Unit) {
    then(get().await())
}

actual fun FirestoreQuery.where(fieldPath: String, operator: String, value: Any): FirestoreQuery =
    where(fieldPath, operator, value)

actual fun FirestoreQuery.limit(limit: Int): FirestoreQuery = limit(limit)

actual fun FirestoreQuery.start(at: FirestoreDocumentSnapshot): FirestoreQuery = startAt(at)
actual fun FirestoreQuery.orderedBy(fieldPath: String): FirestoreQuery = orderBy(fieldPath)