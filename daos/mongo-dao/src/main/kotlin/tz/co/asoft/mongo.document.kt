package tz.co.asoft

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import org.bson.BsonDocument
import org.bson.BsonString
import org.bson.Document

fun <T : Entity> T.toDocument(serializer: SerializationStrategy<T>): Document {
    val doc = Document.parse(Json.stringify(serializer, this))
    if (uid != null) doc.append("_id", uid)
    return doc
}

fun <T : Entity> Document.to(serializer: DeserializationStrategy<T>): T {
    set("uid", get("_id"))
    return Json.parse(serializer, toJson())
}

fun <T : Entity> Collection<Document>.to(serializer: DeserializationStrategy<T>) = map { it.to(serializer) }

fun <T : Entity> Collection<T>.toDocuments(deserializer: SerializationStrategy<T>) = map { it.toDocument(deserializer) }

fun <T : Entity> T.toBsonDocument(serializer: SerializationStrategy<T>): BsonDocument {
    val doc = BsonDocument.parse(Json.stringify(serializer, this))
    if (uid != null) doc.append("_id", BsonString(uid))
    return doc
}

fun <T : Entity> Collection<BsonDocument>.to(serializer: KSerializer<T>) = map { it.to(serializer) }

fun <T : Entity> BsonDocument.to(deserializer: DeserializationStrategy<T>): T {
    set("uid", get("_id"))
    return Json.parse(deserializer, toJson())
}

fun <T : Entity> Collection<T>.toBsonDocuments(serializer: SerializationStrategy<T>) = map { it.toBsonDocument(serializer) }