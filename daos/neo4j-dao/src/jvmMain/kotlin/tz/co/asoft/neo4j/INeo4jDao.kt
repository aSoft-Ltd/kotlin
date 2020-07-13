package tz.co.asoft.neo4j

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.neo4j.ogm.cypher.ComparisonOperator
import org.neo4j.ogm.cypher.Filter
import org.neo4j.ogm.cypher.query.Pagination
import org.neo4j.ogm.session.Session
import tz.co.asoft.paging.PageLoader
import tz.co.asoft.persist.dao.IDao
import kotlin.reflect.KClass

interface INeo4jDao<T : Neo4JEntity> : IDao<T> {

    val clazz: KClass<T>
    val session: Session
    val depth get() = 10

    override suspend fun create(list: Collection<T>) = withContext(Dispatchers.IO) {
        session.save(list, depth)
        val noIds = list.filter { it.uid.isBlank() }.map { it.apply { uid = id.toString() } }
        if (noIds.isNotEmpty()) session.save(noIds, depth)
        session.clear()
        list.toList()
    }

    override suspend fun create(t: T) = create(listOf(t)).first()

    override suspend fun edit(list: Collection<T>) = create(list)

    override suspend fun edit(t: T) = edit(listOf(t)).first()

    override suspend fun wipe(list: Collection<T>) = withContext(Dispatchers.IO) {
        session.delete(list)
        session.clear()
        list.toList()
    }

    override suspend fun wipe(t: T) = wipe(listOf(t)).first()

    override suspend fun load(ids: Collection<Any>): List<T> = coroutineScope {
        ids.toSet().map { async { load(it.toString()) } }.mapNotNull { it.await() }
    }

    override suspend fun load(id: String): T? = withContext(Dispatchers.IO) {
        val filter = Filter("uid", ComparisonOperator.EQUALS, id)
        session.loadAll(clazz.java, filter, depth).firstOrNull().apply { session.clear() }
    }

    override suspend fun load(id: Number) = load(id.toString())

    override suspend fun all(): List<T> = withContext(Dispatchers.IO) {
        val filter = Filter("delete", ComparisonOperator.EQUALS, false)
        session.loadAll(clazz.java, filter, depth).toList().apply { session.clear() }
    }

    override suspend fun paged(pageNumber: Int, pageSize: Int) = withContext(Dispatchers.IO) {
        val pagination = Pagination(pageNumber, pageSize)
        session.loadAll(clazz.java, pagination, depth).toList().apply { session.clear() }
    }

    override suspend fun allDeleted(): List<T> = withContext(Dispatchers.IO) {
        val filter = Filter("delete", ComparisonOperator.EQUALS, true)
        session.loadAll(clazz.java, filter, depth).toList().apply { session.clear() }
    }

    override fun pageLoader(predicate: (T) -> Boolean): PageLoader<*, T> =
        Neo4jPageLoader(session, clazz, depth, predicate)
}