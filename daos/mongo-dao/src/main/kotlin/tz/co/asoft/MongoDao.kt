package tz.co.asoft

import com.mongodb.client.model.Filters.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer

open class MongoDao<T : Entity>(
    override val options: MongoOptions,
    override val serializer: KSerializer<T>,
    collection: String
) : IMongoDao<T> {
    open val collection = db.getCollection(collection)
    override suspend fun create(list: Collection<T>) = withContext(Dispatchers.IO) {
        val docs = list.toDocuments(serializer)
        collection.insertMany(docs)
        docs.to(serializer)
    }

    override suspend fun create(t: T) = withContext(Dispatchers.IO) {
        val doc = t.toDocument(serializer)
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

    override suspend fun delete(t: T): T = edit(listOf(t)).first()

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
        }.limit(size).mapNotNull { it.to(serializer) }
    }

    override fun pageLoader(predicate: (T) -> Boolean): PageLoader<*, T> = MongoPageLoader(collection, serializer, predicate)
}