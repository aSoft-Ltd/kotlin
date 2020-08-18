package tz.co.asoft

import kotlinx.serialization.KSerializer

interface IMongoDao<T : Entity> : IDao<T> {
    val options: MongoOptions
    val serializer: KSerializer<T>
    val client get() = options.toClient()
    val db get() = client.getDatabase(options.database)
}