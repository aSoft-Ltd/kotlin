package tz.co.asoft.firebase.firestore

import tz.co.asoft.firebase.firestore.Query
import tz.co.asoft.firebase.firestore.DocumentSnapshot
import tz.co.asoft.firebase.firestore.QuerySnapshot

actual open class Query

actual fun Query.where(
    fieldPath: String,
    operator: String,
    value: Any
): Query {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun Query.limit(limit: Int): Query {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual suspend fun Query.get(then: suspend (QuerySnapshot) -> Unit) {
}

actual suspend fun Query.fetch(): QuerySnapshot = TODO()
actual fun Query.start(at: DocumentSnapshot): Query {
    TODO("Not yet implemented")
}

actual fun Query.orderedBy(fieldPath: String): Query {
    TODO("Not yet implemented")
}