package tz.co.asoft

import kotlinx.serialization.KSerializer

internal class FirebasePageLoader<D : Entity>(
    private val serializer: KSerializer<D>,
    private val collection: FirestoreCollectionReference,
    override val predicate: (D) -> Boolean = { !it.deleted }
) : PageLoader<FirestoreDocumentSnapshot, D> {

    override fun pager(
        pageSize: Int,
        extraPages: Int,
        showTmpPages: Boolean
    ): Pager<FirestoreDocumentSnapshot, D> {
        val fetcher = PageFetcher(this, pageSize)
        return Pager(fetcher, showTmpPages)
    }

    private suspend fun loadPage(
        pageSize: Int,
        at: FirestoreDocumentSnapshot?
    ): List<FirestoreDocumentSnapshot> {
        val snaps = mutableListOf<FirestoreDocumentSnapshot>()
        val query = collection.orderedBy("uid")
        val unfilteredSnaps = if (at == null) {
            query.limit(pageSize + 1).fetch().documents
        } else {
            query.start(at).limit(pageSize + 1).fetch().documents
        }
        val documentPredicate: (FirestoreDocumentSnapshot) -> Boolean = {
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

    override suspend fun firstPage(pageSize: Int): Page<FirestoreDocumentSnapshot, D> {
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

    override suspend fun nextOf(node: Page<FirestoreDocumentSnapshot, D>): Page<FirestoreDocumentSnapshot, D> {
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

    override suspend fun prevOf(node: Page<FirestoreDocumentSnapshot, D>): Page<FirestoreDocumentSnapshot, D> {
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