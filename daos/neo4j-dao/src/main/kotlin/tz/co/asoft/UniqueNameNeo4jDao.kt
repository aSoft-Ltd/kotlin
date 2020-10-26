package tz.co.asoft

import org.neo4j.ogm.config.Configuration
import org.neo4j.ogm.cypher.ComparisonOperator
import org.neo4j.ogm.cypher.Filter
import kotlin.reflect.KClass

open class UniqueNameNeo4jDao<T : NamedEntity>(
    config: Configuration,
    override val clazz: KClass<T>,
    override val depth: Int = 10,
    vararg clazzes: KClass<*>
) : Neo4jDao<T>(config, clazz, depth, *clazzes) {

    constructor(
        protocal: String = "http",
        username: String = "neo4j",
        password: String = "neo4j",
        service: String,
        port: Int = 7474,
        depth: Int = 10,
        clazz: KClass<T>,
        vararg clazzes: KClass<*>
    ) : this(
        config = Configuration.Builder().uri("$protocal://$username:$password@$service:$port").build(),
        clazz = clazz,
        depth = depth,
        clazzes = *clazzes
    )

    override suspend fun create(list: Collection<T>): List<T> {
        val found = list.flatMap {
            session.loadAll(clazz.java, Filter("name", ComparisonOperator.EQUALS, it.name))
        }.map { it.name }

        if (found.size == 1) throw Exception("Entity with name ${found.first()} already exist in the database")

        if (found.isNotEmpty()) throw Exception("Entities with name(s) $found already exist in the database")

        return super.create(list)
    }

    override suspend fun edit(list: Collection<T>): List<T> {
        val found = list.flatMap {
            session.loadAll(clazz.java, Filter("name", ComparisonOperator.EQUALS, it.name))
        }.filterNot { obj ->
            list.map { it.uid }.contains(obj.uid)
        }.map { it.name }

        if (found.size == 1) throw Exception("Entity with name ${found.first()} already exist in the database")

        if (found.isNotEmpty()) throw Exception("Entities with name(s) $found already exist in the database")

        return super.create(list)
    }
}