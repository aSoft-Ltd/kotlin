package tz.co.asoft

import org.neo4j.ogm.session.Session
import kotlin.reflect.KClass

interface INeo4jDao<T : Neo4JEntity> : IDao<T> {
    val clazz: KClass<T>
    val session: Session
    val depth get() = 10
}