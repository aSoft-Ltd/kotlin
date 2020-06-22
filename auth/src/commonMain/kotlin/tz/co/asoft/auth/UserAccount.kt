package tz.co.asoft.auth

import kotlinx.serialization.Serializable
import tz.co.asoft.auth.tools.name.Name
import tz.co.asoft.neo4j.Neo4JEntity
import tz.co.asoft.neo4j.annotations.GeneratedValue
import tz.co.asoft.neo4j.annotations.Id
import tz.co.asoft.neo4j.annotations.NodeEntity

@Serializable
@NodeEntity
open class UserAccount(
    @Id
    @GeneratedValue
    override var id: Long? = null,
    override var uid: String = "",
    val name: String,
    val permits: List<String> = listOf()
) : Neo4JEntity {
    fun ref() = UserAccountRef(
        uid = uid,
        name = name
    )
}