package tz.co.asoft.auth

import kotlinx.serialization.Serializable
import tz.co.asoft.neo4j.Neo4JEntity
import tz.co.asoft.neo4j.annotations.NodeEntity

@Serializable
@NodeEntity
data class Role(
    override var id: Long? = null,
    override var uid: String = "",
    var name: String,
    var permits: List<String> = mutableListOf(),
    override var deleted: Boolean = false
) : Neo4JEntity