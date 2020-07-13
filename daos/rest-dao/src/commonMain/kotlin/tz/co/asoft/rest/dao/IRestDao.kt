package tz.co.asoft.rest.dao

import io.ktor.client.HttpClient
import io.ktor.client.request.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.list
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.builtins.serializer
import tz.co.asoft.paging.PageLoader
import tz.co.asoft.paging.VKey
import tz.co.asoft.persist.dao.IDao
import tz.co.asoft.persist.model.Entity
import tz.co.asoft.result.Result
import tz.co.asoft.result.Result.Companion.RJson
import tz.co.asoft.result.respond
import kotlin.coroutines.AbstractCoroutineContextKey

interface IRestDao<T : Entity> : IDao<T> {
    val client: HttpClient
    val url: String
    val version: String
    val root: String
    val subRoot: String?
    val headers: Map<String, String>?
    val serializer: KSerializer<T>

    val path get() = "$version/$root" + if (subRoot != null) "/$subRoot" else ""

    fun HttpRequestBuilder.appendHeaders() {
        this@IRestDao.headers?.forEach { (k, v) ->
            header(k, v)
        }
    }

    override suspend fun create(list: Collection<T>): List<T> {
        val json = client.post<String>("$url/$path") {
            appendHeaders()
            body = RJson.stringify(serializer.list, list.toList())
        }
        return Result.parse(serializer.list, json).respond()
    }

    override suspend fun create(t: T) = create(listOf(t)).first()

    override suspend fun edit(list: Collection<T>): List<T> {
        val json = client.patch<String>("$url/$path") {
            appendHeaders()
            body = RJson.stringify(serializer.list, list.toList())
        }
        return Result.parse(serializer.list, json).respond()
    }

    override suspend fun edit(t: T) = edit(listOf(t)).first()

    override suspend fun delete(list: Collection<T>): List<T> {
        val json = client.delete<String>("$url/$path") {
            appendHeaders()
            body = RJson.stringify(serializer.list, list.toList())
        }
        return Result.parse(serializer.list, json).respond()
    }

    override suspend fun delete(t: T) = delete(listOf(t)).first()

    override suspend fun wipe(list: Collection<T>): List<T> {
        val json = client.post<String>("$url/$path/wipe") {
            appendHeaders()
            body = RJson.stringify(serializer.list, list.toList())
        }
        return Result.parse(serializer.list, json).respond()
    }

    override suspend fun wipe(t: T): T = wipe(listOf(t)).first()

    override suspend fun load(ids: Collection<Any>): List<T> {
        val stringIds = ids.map { it.toString() }.filter { it.isNotBlank() }
        if (stringIds.isNotEmpty()) {
            return emptyList()
        }
        val json = client.post<String>("$url/$path/load") {
            appendHeaders()
            body = RJson.stringify(String.serializer().list, stringIds)
        }
        return Result.parse(serializer.list, json).respond()
    }

    override suspend fun load(id: String): T? {
        val json = client.get<String>("$url/$path/$id") {
            appendHeaders()
        }
        return Result.parse(serializer.nullable, json).respond()
    }

    override suspend fun paged(pageNumber: Int, pageSize: Int): List<T> {
        val json = client.get<String>("$url/$path/paged/$pageNumber/$pageSize") {
            appendHeaders()
        }
        return Result.parse(serializer.list, json).respond()
    }

    suspend fun page(pi: PageRequestInfo): List<T> {
        val json = client.post<String>("$url/$path/page") {
            appendHeaders()
            body = RJson.stringify(PageRequestInfo.serializer(), pi)
        }
        return Result.parse(serializer.list, json).respond()
    }

    override suspend fun all(): List<T> {
        val json = client.get<String>("$url/$path/all") {
            appendHeaders()
        }
        return Result.parse(serializer.list, json).respond()
    }

    override suspend fun allDeleted(): List<T> {
        val json = client.get<String>("$url/$path/all-deleted") {
            appendHeaders()
        }
        return Result.parse(serializer.list, json).respond()
    }

    override fun pageLoader(predicate: (T) -> Boolean): PageLoader<*, T> =
        RestPageLoader(this, predicate)
}