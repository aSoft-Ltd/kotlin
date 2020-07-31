package tz.co.asoft

import io.ktor.client.HttpClient
import kotlinx.serialization.KSerializer

interface IRestDao<T : Entity> : IDao<T> {
    val client: HttpClient
    val url: String
    val version: String
    val root: String
    val subRoot: String?
    val headers: Map<String, String>?
    val serializer: KSerializer<T>

    val path get() = "$version/$root" + if (subRoot != null) "/$subRoot" else ""

    suspend fun page(pi: RestPageRequestInfo): List<T>
}