package tz.co.asoft.neo4j

import org.neo4j.ogm.config.Configuration
import org.neo4j.ogm.session.SessionFactory
import tz.co.asoft.persist.dao.PagedDao
import kotlin.reflect.KClass

class PagedNeo4jDao<T:Neo4JEntity>(
    config: Configuration,
    override val clazz: KClass<T>,
    override val depth: Int = 10,
    vararg clazzes: KClass<*>
) : PagedDao<Int,T>(), INeo4jDao<T> {
    override val session by lazy {
        val klasses = (clazzes.toSet() + clazz).map { it.java.`package`.name }
        SessionFactory(config, *klasses.toTypedArray()).openSession()
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {

        return paged()
    }
}