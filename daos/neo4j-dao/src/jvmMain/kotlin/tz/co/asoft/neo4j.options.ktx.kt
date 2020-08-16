package tz.co.asoft

import org.neo4j.ogm.config.Configuration

fun Neo4jOptions.configuration() = Configuration.Builder().uri("$protocol://$username:$password@$ip:$port").build()

fun Map<String, *>.toNeo4jOptions(): Neo4jOptions {
    val protocol: String by this
    val username: String by this
    val password: String by this
    val ip: String by this
    val port: String by this
    return Neo4jOptions(protocol, username, password, ip, port.toInt())
}