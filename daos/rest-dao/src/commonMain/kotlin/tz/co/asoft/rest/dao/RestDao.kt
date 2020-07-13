package tz.co.asoft.rest.dao

import io.ktor.client.HttpClient
import kotlinx.serialization.KSerializer
import tz.co.asoft.persist.model.Entity

open class RestDao<T : Entity>(
    override val url: String,
    override val version: String,
    override val serializer: KSerializer<T>,
    override val root: String,
    override val subRoot: String?,
    override val headers: Map<String, String>? = null
) : IRestDao<T> {
    override val client = HttpClient { }
}