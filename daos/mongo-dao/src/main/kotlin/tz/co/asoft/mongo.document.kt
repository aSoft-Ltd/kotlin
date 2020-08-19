package tz.co.asoft

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import org.bson.Document
import org.bson.types.ObjectId

fun <T : Entity> T.toDocument(serializer: SerializationStrategy<T>): Document {
    val doc = Document.parse(Json.stringify(serializer, this))
    if (uid != null) doc.append("_id", uid)
    return doc
}

fun <T : Entity> Document.to(serializer: DeserializationStrategy<T>): T {
    val objId = when(val id = get("_id")) {
        is ObjectId -> id.toHexString()
        else -> id.toString()
    }
    set("uid", objId)
    return Json.parse(serializer, toJson())
}

fun <T : Entity> Collection<Document>.to(serializer: DeserializationStrategy<T>) = map { it.to(serializer) }

fun <T : Entity> Collection<T>.toDocuments(deserializer: SerializationStrategy<T>) = map { it.toDocument(deserializer) }