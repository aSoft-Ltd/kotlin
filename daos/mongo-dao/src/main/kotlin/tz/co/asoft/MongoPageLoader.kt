package tz.co.asoft

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters.gte
import kotlinx.serialization.KSerializer
import org.bson.Document

internal class MongoPageLoader<D : Entity>(
    val collection: MongoCollection<Document>,
    val serializer: KSerializer<D>,
    override val predicate: (D) -> Boolean
) : PageLoader<String, D> {

    private suspend fun loadPage(pageSize: Int, at: String?): Pair<String, List<D>> {
        val key = if (at == null || at.isBlank()) "" else at
        val docs = if (key.isBlank()) {
            collection.find()
        } else {
            collection.find(gte("uid", key))
        }.limit(pageSize)
        val nodes = mutableListOf<D>()
        val allNodes: List<D> = docs.mapNotNull { it.to(serializer) }
        val startIndex = if (key == null) 0 else allNodes.indexOfFirst { it.uid == key }
        val unfilteredNodes = allNodes.subList(startIndex, allNodes.size)

        val lastDoc = unfilteredNodes.lastOrNull() ?: return key to nodes
        val filteredSnaps = (unfilteredNodes - lastDoc).filter(predicate)
        nodes += filteredSnaps
        if (nodes.size >= pageSize) {
            return key to nodes + listOf(lastDoc).filter(predicate)
        }

        if (unfilteredNodes.size < pageSize) {
            return key to nodes + listOf(lastDoc).filter(predicate)
        }

        val pair = loadPage(pageSize - nodes.size, key)
        return pair.first to nodes + pair.second
    }

    override suspend fun prevOf(node: Page<String, D>): Page<String, D> {
        val key = node.prev?.key ?: throw Exception("Can't go prev with null key")
        val data = loadPage(node.pageSize, key)
        val nextKey = if (data.second.size < (node.pageSize + 1)) null else data.first
        return Page(
            data = if (data.second.size < node.pageSize) data.second else data.second.dropLast(1),
            key = key,
            prev = node.prev?.prev,
            next = node,
            nextKey = nextKey,
            pageSize = node.pageSize
        )
    }

    override suspend fun nextOf(node: Page<String, D>): Page<String, D> {
        val key = node.nextKey ?: throw Exception("Can't go next with null key")
        val data = loadPage(node.pageSize, key)
        val nextKey = if (data.second.size < (node.pageSize + 1)) null else data.first
        return Page(
            data = if (data.second.size < node.pageSize) data.second else data.second.dropLast(1),
            key = key,
            prev = node,
            next = null,
            nextKey = nextKey,
            pageSize = node.pageSize
        )
    }

    override suspend fun firstPage(pageSize: Int): Page<String, D> {
        val data = loadPage(pageSize, null)
        val nextKey = if (data.second.size < (pageSize + 1)) null else data.first
        return Page(
            data = if (data.second.size < pageSize) data.second else data.second.dropLast(1),
            key = null,
            prev = null,
            next = null,
            nextKey = nextKey,
            pageSize = pageSize
        )
    }
}