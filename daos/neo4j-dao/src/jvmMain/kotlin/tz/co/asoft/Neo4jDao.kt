package tz.co.asoft

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.neo4j.ogm.config.Configuration
import org.neo4j.ogm.cypher.ComparisonOperator
import org.neo4j.ogm.cypher.Filter
import org.neo4j.ogm.session.SessionFactory
import kotlin.reflect.KClass

open class Neo4JDao<T : Neo4JEntity>(
    config: Configuration,
    override val clazz: KClass<T>,
    override val depth: Int = 10,
    vararg clazzes: KClass<*>
) : INeo4jDao<T> {
    override val session by lazy {
        val klasses = (clazzes.toSet() + clazz).map { it.java.`package`.name }
        SessionFactory(config, *klasses.toTypedArray()).openSession()
    }

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

    override suspend fun delete(list: Collection<T>): List<T> = edit(list.map {
        it.deleted = true
        it
    })

    override suspend fun delete(t: T) = listOf(t).first()

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
        val filter = Filter("deleted", ComparisonOperator.EQUALS, false)
        session.loadAll(clazz.java, filter, depth).toList().apply { session.clear() }
    }

    override suspend fun allDeleted(): List<T> = withContext(Dispatchers.IO) {
        val filter = Filter("deleted", ComparisonOperator.EQUALS, true)
        session.loadAll(clazz.java, filter, depth).toList().apply { session.clear() }
    }

    override fun pageLoader(predicate: (T) -> Boolean): PageLoader<*, T> = Neo4jPageLoader(session, clazz, depth, predicate)
}