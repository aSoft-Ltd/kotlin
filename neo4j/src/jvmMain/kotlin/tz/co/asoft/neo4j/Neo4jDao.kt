package tz.co.asoft.neo4j

import org.neo4j.ogm.config.Configuration
import org.neo4j.ogm.session.SessionFactory
import tz.co.asoft.persist.dao.IDao
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
}