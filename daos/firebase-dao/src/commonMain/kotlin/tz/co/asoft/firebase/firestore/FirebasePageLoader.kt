package tz.co.asoft.firebase.firestore

import kotlinx.serialization.KSerializer
import tz.co.asoft.firebase.firestore.collection.CollectionReference
import tz.co.asoft.firebase.firestore.query.fetch
import tz.co.asoft.firebase.firestore.query.limit
import tz.co.asoft.firebase.firestore.query.orderedBy
import tz.co.asoft.firebase.firestore.query.start
import tz.co.asoft.firebase.firestore.snapshot.DocumentSnapshot
import tz.co.asoft.firebase.firestore.snapshot.documents
import tz.co.asoft.firebase.firestore.snapshot.toObject
import tz.co.asoft.paging.Page
import tz.co.asoft.paging.PageFetcher
import tz.co.asoft.paging.PageLoader
import tz.co.asoft.paging.Pager
import tz.co.asoft.persist.model.Entity

internal class FirebasePageLoader<D : Entity>(
    private val serializer: KSerializer<D>,
    private val collection: CollectionReference,
    override val predicate: (D) -> Boolean = { !it.deleted }
) : PageLoader<DocumentSnapshot, D> {

    override fun pager(
        pageSize: Int,
        extraPages: Int,
        showTmpPages: Boolean
    ): Pager<DocumentSnapshot, D> {
        val fetcher = PageFetcher(this, pageSize)
        return Pager(fetcher, showTmpPages)
    }

    private suspend fun loadPage(pageSize: Int, at: DocumentSnapshot?): List<DocumentSnapshot> {
        val snaps = mutableListOf<DocumentSnapshot>()
        val query = collection.orderedBy("uid")
        val unfilteredSnaps = if (at == null) {
            query.limit(pageSize + 1).fetch().documents
        } else {
            query.start(at).limit(pageSize + 1).fetch().documents
        }
        val documentPredicate: (DocumentSnapshot) -> Boolean = {
            it.toObject(serializer)?.let(predicate) == true
        }
        val lastDoc = unfilteredSnaps.lastOrNull() ?: return snaps
        val filteredSnaps = (unfilteredSnaps - lastDoc).filter(documentPredicate)
        snaps += filteredSnaps
        if (snaps.size >= pageSize) {
            return snaps + listOf(lastDoc).filter(documentPredicate)
        }

        if (unfilteredSnaps.size < pageSize) {
            return snaps + listOf(lastDoc).filter(documentPredicate)
        }

        return snaps + loadPage(pageSize - snaps.size, lastDoc)
    }

    override suspend fun firstPage(pageSize: Int): Page<DocumentSnapshot, D> {
        val snaps = loadPage(pageSize, null)
        val key = snaps.firstOrNull()
        val users = snaps.mapNotNull { it.toObject(serializer) }
        val nextKey = if (users.size < (pageSize + 1)) null else snaps.last()
        return Page(
            data = if (users.size < pageSize) users else users.dropLast(1),
            key = key,
            nextKey = nextKey,
            pageSize = pageSize,
            next = null,
            prev = null
        )
    }

    override suspend fun nextOf(node: Page<DocumentSnapshot, D>): Page<DocumentSnapshot, D> {
        val key = node.nextKey ?: throw Exception("Can't go to next node")
        val snaps = loadPage(node.pageSize, key)
        val users = snaps.mapNotNull { it.toObject(serializer) }
        val nextKey = if (users.size < (node.pageSize + 1)) null else snaps.last()
        return Page(
            data = if (users.size < node.pageSize) users else users.dropLast(1),
            key = key,
            nextKey = nextKey,
            pageSize = node.pageSize,
            prev = node,
            next = null
        )
    }

    override suspend fun prevOf(node: Page<DocumentSnapshot, D>): Page<DocumentSnapshot, D> {
        val key = node.prev?.key ?: throw Exception("Can't go to prev page")
        val snaps = loadPage(node.pageSize, key)
        val users = snaps.mapNotNull { it.toObject(serializer) }
        val nextKey = if (users.size < (node.pageSize + 1)) null else snaps.last()
        return Page(
            data = if (users.size < node.pageSize) users else users.dropLast(1),
            key = key,
            nextKey = nextKey,
            pageSize = node.pageSize,
            prev = node.prev?.prev,
            next = node
        )
    }
}