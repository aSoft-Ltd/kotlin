@file:JvmName("CommonQuery")

package tz.co.asoft

import kotlin.jvm.JvmName

expect open class FirestoreQuery

expect fun FirestoreQuery.where(fieldPath: String, operator: String, value: Any): FirestoreQuery

expect fun FirestoreQuery.limit(limit: Int): FirestoreQuery

expect fun FirestoreQuery.start(at: FirestoreDocumentSnapshot): FirestoreQuery

expect fun FirestoreQuery.orderedBy(fieldPath: String) : FirestoreQuery

@Deprecated("Use fetch")
expect suspend fun FirestoreQuery.get(then: suspend (FirestoreQuerySnapshot) -> Unit)

expect suspend fun FirestoreQuery.fetch(): FirestoreQuerySnapshot

fun FirestoreQuery.where(qp: FirestoreQueryParam): FirestoreQuery {
    val op = when (qp.op) {
        FirestoreQueryParam.Operator.LessThan -> "<"
        FirestoreQueryParam.Operator.LessThanOrEqual -> "<="
        FirestoreQueryParam.Operator.Equals -> "=="
        FirestoreQueryParam.Operator.GreaterThanOrEqual -> ">="
        FirestoreQueryParam.Operator.GreaterThan -> ">"
        FirestoreQueryParam.Operator.ArrayContains -> "array-contains"
    }
    return where(qp.left, op, qp.right)
}