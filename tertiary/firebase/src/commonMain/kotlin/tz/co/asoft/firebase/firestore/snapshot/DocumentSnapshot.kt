package tz.co.asoft.firebase.firestore.snapshot

import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.serializer
import tz.co.asoft.firebase.firestore.serializer.json

expect open class DocumentSnapshot

expect val DocumentSnapshot.id: String
expect fun DocumentSnapshot.toJson(): String

fun <T> DocumentSnapshot.toObject(serializer: KSerializer<T>): T = json.parse(serializer, toJson())

@Deprecated("Pass the serializer explicitly")
@ImplicitReflectionSerializer
inline fun <reified T : Any> DocumentSnapshot.toObject(): T? = toObject(T::class.serializer())

expect fun DocumentSnapshot.get(fieldPath: String): Any?
expect fun DocumentSnapshot.data(): Map<String, Any>?