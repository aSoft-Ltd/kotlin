package tz.co.asoft

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import org.bson.Document
import org.bson.types.ObjectId

fun <T : Entity> T.toDocument(serializer: SerializationStrategy<T>): Document {
    return Document.parse(Json.stringify(serializer, this))
}

fun <T : Entity> Document.to(serializer: DeserializationStrategy<T>): T = Json.parse(serializer, toJson())

fun <T : Entity> Collection<Document>.to(serializer: DeserializationStrategy<T>) = map { it.to(serializer) }

fun <T : Entity> Collection<T>.toDocuments(deserializer: SerializationStrategy<T>) = map { it.toDocument(deserializer) }