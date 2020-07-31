package tz.co.asoft

import kotlinx.serialization.KSerializer

expect open class FirestoreDocumentSnapshot

expect val FirestoreDocumentSnapshot.id: String
expect fun FirestoreDocumentSnapshot.toJson(): String?

fun <T> FirestoreDocumentSnapshot.toObject(serializer: KSerializer<T>): T? =
    toJson()?.let { Json.parse(serializer, it) }

expect fun FirestoreDocumentSnapshot.get(fieldPath: String): Any?
expect fun FirestoreDocumentSnapshot.data(): Map<String, Any>?