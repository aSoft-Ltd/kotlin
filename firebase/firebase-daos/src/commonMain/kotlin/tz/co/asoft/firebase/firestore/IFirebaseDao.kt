package tz.co.asoft.firebase.firestore

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.KSerializer
import tz.co.asoft.firebase.firestore.batch.put
import tz.co.asoft.firebase.firestore.batch.submit
import tz.co.asoft.firebase.firestore.collection.doc
import tz.co.asoft.firebase.firestore.document.fetch
import tz.co.asoft.firebase.firestore.document.id
import tz.co.asoft.firebase.firestore.query.*
import tz.co.asoft.firebase.firestore.snapshot.documents
import tz.co.asoft.firebase.firestore.snapshot.toObject
import tz.co.asoft.firebase.firestore.snapshot.toObjects
import tz.co.asoft.persist.dao.IDao
import tz.co.asoft.persist.model.Entity
import kotlin.reflect.KProperty

interface IFirebaseDao<T : Entity> : IDao<T> {

    val firestore: FirebaseFirestore

    val serializer: KSerializer<T>

    val collectionName: String

    val batch get() = firestore.batch()

    val collection get() = firestore.collection(collectionName)

    fun docRef(id: String) = collection.doc(id)

    override suspend fun create(list: Collection<T>): List<T> = batch.run {
        list.forEach {
            val doc = if (it.uid.isNotBlank()) collection.doc(it.uid) else collection.doc()
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

    override suspend fun wipe(list: Collection<T>): List<T> = batch.run {
        list.forEach { delete(docRef(it.uid)) }
        submit()
        return list.toList()
    }

    override suspend fun wipe(t: T): T = wipe(listOf(t)).first()

    override suspend fun load(ids: Collection<Any>): List<T> = coroutineScope {
        ids.toSet().filter {
            it.toString().isNotBlank()
        }.mapNotNull {
            async { docRef(it.toString()).fetch().toObject(serializer) }
        }.mapNotNull {
            it.await()
        }
    }

    override suspend fun load(id: String): T? = load(listOf(id)).firstOrNull()

    override suspend fun paged(pageNumber: Int, pageSize: Int): List<T> {
        val query = collection.where("deleted" equals false).limit(pageNumber * pageSize)
        val docs = query.fetch().documents.chunked(pageSize).getOrNull(0)
        return docs?.mapNotNull { it.toObject(serializer) } ?: listOf()
    }

    override suspend fun all() = collection.where(
        "deleted" equals false
    ).fetch().toObjects(serializer)

    override suspend fun allDeleted() = collection.where(
        "deleted" equals true
    ).fetch().toObjects(serializer)
}