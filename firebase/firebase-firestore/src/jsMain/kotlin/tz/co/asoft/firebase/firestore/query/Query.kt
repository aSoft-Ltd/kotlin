package tz.co.asoft.firebase.firestore.query

import tz.co.asoft.firebase.firestore.snapshot.QuerySnapshot
import kotlinx.coroutines.await
import tz.co.asoft.firebase.firestore.snapshot.DocumentSnapshot
import kotlin.js.Promise

actual open external class Query {
    open fun get(): Promise<QuerySnapshot>
    fun where(field: String, opStr: String, value: Any): Query
    fun limit(limit: Number): Query
    fun startAt(snapshot: DocumentSnapshot): Query
    fun startAfter(snapshot: DocumentSnapshot): Query
    fun endBefore(snapshot: DocumentSnapshot): Query
    fun endAt(snapshot: DocumentSnapshot): Query
    fun orderBy(field: String): Query
}

actual suspend fun Query.fetch(): QuerySnapshot = get().await()

actual suspend fun Query.get(then: suspend (QuerySnapshot) -> Unit) {
    then(get().await())
}

actual fun Query.where(fieldPath: String, operator: String, value: Any): Query =
    where(fieldPath, operator, value)

actual fun Query.limit(limit: Int): Query = limit(limit)

actual fun Query.start(at: DocumentSnapshot): Query = startAt(at)
actual fun Query.orderedBy(fieldPath: String): Query = orderBy(fieldPath)