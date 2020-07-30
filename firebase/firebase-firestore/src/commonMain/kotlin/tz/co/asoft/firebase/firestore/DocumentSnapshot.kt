package tz.co.asoft.firebase.firestore

import kotlinx.serialization.KSerializer

expect open class DocumentSnapshot

expect val DocumentSnapshot.id: String
expect fun DocumentSnapshot.toJson(): String?

fun <T> DocumentSnapshot.toObject(serializer: KSerializer<T>): T? =
    toJson()?.let { json.parse(serializer, it) }

expect fun DocumentSnapshot.get(fieldPath: String): Any?
expect fun DocumentSnapshot.data(): Map<String, Any>?