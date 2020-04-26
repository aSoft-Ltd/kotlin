package tz.co.asoft.neo4j

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.neo4j.ogm.config.Configuration
import org.neo4j.ogm.cypher.ComparisonOperator
import org.neo4j.ogm.cypher.Filter
import org.neo4j.ogm.session.SessionFactory
import tz.co.asoft.persist.dao.IDao
import kotlin.reflect.KClass

open class Neo4JDao<T : Neo4JEntity>(
        private val clazz: KClass<T>,
        config: Configuration,
        vararg clazzes: KClass<*>
) : IDao<T> {

    open val session by lazy {
        SessionFactory(config, *clazzes.map { it.java.`package`.name }.toTypedArray()).openSession()
    }

    open val depth = 10

    override suspend fun create(list: List<T>) = withContext(Dispatchers.IO) {
        session.save(list, depth)
        val noIds = list.filter { it.uid.isBlank() }.map { it.apply { uid = id.toString() } }
        if (noIds.isNotEmpty()) session.save(noIds, depth)
        session.clear()
        list
    }

    override suspend fun create(t: T) = create(listOf(t)).first()

    override suspend fun edit(list: List<T>) = create(list)

    override suspend fun edit(t: T) = edit(listOf(t)).first()

    override suspend fun wipe(list: List<T>) = withContext(Dispatchers.IO) {
        session.delete(list)
        session.clear()
        list
    }

    override suspend fun wipe(t: T) = wipe(listOf(t)).first()

    override suspend fun delete(list: List<T>) = wipe(list)

    override suspend fun delete(t: T) = delete(listOf(t)).first()

    override suspend fun load(ids: List<Any>): List<T> = coroutineScope {
        ids.map { async { load(it.toString()) } }.mapNotNull { it.await() }
    }

    override suspend fun load(id: String): T? = withContext(Dispatchers.IO) {
        val filter = Filter("uid", ComparisonOperator.EQUALS, id)
        session.loadAll(clazz.java, filter, depth).firstOrNull().apply { session.clear() }
    }

    override suspend fun load(id: Number) = load(id.toString())

    override suspend fun all(): List<T> = withContext(Dispatchers.IO) {
        session.loadAll(clazz.java, depth).toList().apply { session.clear() }
    }
}