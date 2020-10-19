package tz.co.asoft

import com.mongodb.client.MongoDatabase
import kotlinx.serialization.KSerializer

interface IMongoDao<T : Entity> : IDao<T> {
    val serializer: KSerializer<T>
    val db: MongoDatabase
}