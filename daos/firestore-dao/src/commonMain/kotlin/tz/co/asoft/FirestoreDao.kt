package tz.co.asoft

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.KSerializer

open class FirestoreDao<T : Entity>(
    override val firestore: FirebaseFirestore,
    override val collectionName: String,
    override val serializer: KSerializer<T>
) : IFirestoreDao<T> {
    override suspend fun create(list: Collection<T>): List<T> = batch.run {
        list.forEach {
            val doc = if (it.uid != null) collection.doc(it.uid) else collection.doc()
            put(doc, it.apply { uid = doc.id }, serializer)
        }
        submit()
        list.toList()
    }

    override suspend fun create(t: T): T = create(listOf(t)).first()

    override suspend fun edit(list: Collection<T>): List<T> = batch.run {
        list.forEach { put(collection.doc(it.uid), it, serializer) }
        submit()
        list.toList()
    }

    override suspend fun edit(t: T): T = edit(listOf(t)).first()

    override suspend fun delete(list: Collection<T>): List<T> = edit(list.map {
        it.deleted = true
        it
    })

    override suspend fun delete(t: T): T = delete(listOf(t)).first()

    override suspend fun wipe(list: Collection<T>): List<T> = batch.run {
        list.mapNotNull { it.uid }.forEach { delete(docRef(it)) }
        submit()
        return list.toList()
    }

    override suspend fun wipe(t: T): T = wipe(listOf(t)).first()

    override suspend fun load(ids: Collection<Any>): List<T> = coroutineScope {
        ids.toSet().filter {
            it.toString().isNotBlank()
        }.map {
            async { docRef(it.toString()).fetch().toObject(serializer) }
        }.mapNotNull {
            it.await()
        }
    }

    override suspend fun load(startAt: String?, limit: Int) = if (startAt == null) {
        collection.where("deleted" equals false)
    } else {
        val startDoc = collection.doc(startAt).fetch()
        collection.where("deleted" equals false).start(at = startDoc)
    }.limit(limit).fetch().toObjects(serializer)

    override suspend fun load(id: String): T? = load(listOf(id)).firstOrNull()

    override suspend fun all() = collection.where("deleted" equals false).fetch().toObjects(serializer)

    override suspend fun allDeleted() = collection.where("deleted" equals true).fetch().toObjects(serializer)

    override fun pageLoader(predicate: (T) -> Boolean): PageLoader<*, T> = FirebasePageLoader(serializer, collection, predicate)
}