package tz.co.asoft

import com.mongodb.client.model.Filters.*
import com.mongodb.client.model.Indexes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import org.bson.types.ObjectId

open class MongoDao<T : Entity>(
    override val options: MongoOptions,
    override val serializer: KSerializer<T>,
    collection: String
) : IMongoDao<T> {
    open val collection = db.getCollection(collection).apply {
        createIndex(Indexes.ascending("uid"))
    }

    override suspend fun create(list: Collection<T>) = list.map { create(it) }

    override suspend fun create(t: T) = withContext(Dispatchers.IO) {
        val id = ObjectId.get()
        if (t.uid == null) t.uid = id.toHexString()
        val doc = t.toDocument(serializer).apply {
            append("_id", id)
        }
        collection.insertOne(doc)
        doc.to(serializer)
    }

    override suspend fun edit(list: Collection<T>) = list.map { edit(it) }

    override suspend fun edit(t: T) = withContext(Dispatchers.IO) {
        val doc = t.toDocument(serializer)
        collection.replaceOne(eq("uid", t.uid), doc)
        doc.to(serializer)
    }

    override suspend fun delete(list: Collection<T>) = list.map {
        it.deleted = true
        edit(it)
    }

    override suspend fun delete(t: T): T = delete(listOf(t)).first()

    override suspend fun wipe(list: Collection<T>) = list.map { wipe(it) }

    override suspend fun wipe(t: T) = withContext(Dispatchers.IO) {
        collection.deleteOne(eq("uid", t.uid))
        t
    }

    override suspend fun load(ids: Collection<Any>) = ids.mapNotNull { load(it.toString()) }

    override suspend fun load(id: String) = withContext(Dispatchers.IO) {
        val doc = collection.find(eq("uid", id)).first()
        doc?.to(serializer)
    }

    override suspend fun all() = withContext(Dispatchers.IO) {
        val doc = collection.find(eq("deleted", false))
        doc.mapNotNull { it.to(serializer) }
    }

    override suspend fun allDeleted() = withContext(Dispatchers.IO) {
        val doc = collection.find(eq("deleted", true))
        doc.mapNotNull { it.to(serializer) }
    }

    override suspend fun load(startAt: String?, size: Int) = withContext(Dispatchers.IO) {
        if (startAt == null) {
            collection.find(eq("deleted", false))
        } else {
            collection.find(and(gte("uid", startAt), eq("deleted", false)))
        }.sort(eq("uid", 1)).limit(size).mapNotNull { it.to(serializer) }
    }

    override fun pageLoader(predicate: (T) -> Boolean): PageLoader<*, T> = MongoPageLoader(collection, serializer, predicate)
}