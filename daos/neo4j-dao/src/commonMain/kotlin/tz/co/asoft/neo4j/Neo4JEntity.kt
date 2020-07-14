package tz.co.asoft.neo4j

import tz.co.asoft.persist.model.Entity

interface Neo4JEntity : Entity {
    var id: Long?
}