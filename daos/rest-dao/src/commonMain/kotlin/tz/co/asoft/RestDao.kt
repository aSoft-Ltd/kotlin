package tz.co.asoft

import io.ktor.client.HttpClient
import io.ktor.client.request.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.list
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.builtins.serializer

open class RestDao<T : Entity>(
    override val url: String,
    override val version: String,
    override val serializer: KSerializer<T>,
    override val root: String,
    override val subRoot: String?,
    override val headers: Map<String, String>? = null
) : IRestDao<T> {
    override val client = HttpClient { }

    fun HttpRequestBuilder.appendHeaders() {
        this@RestDao.headers?.forEach { (k, v) ->
            header(k, v)
        }
    }

    override suspend fun create(list: Collection<T>): List<T> {
        val json = client.post<String>("$url/$path") {
            appendHeaders()
            body = RJson.stringify(serializer.list, list.toList())
        }
        return Result.parse(serializer.list, json).response()
    }

    override suspend fun create(t: T) = create(listOf(t)).first()

    override suspend fun edit(list: Collection<T>): List<T> {
        val json = client.patch<String>("$url/$path") {
            appendHeaders()
            body = RJson.stringify(serializer.list, list.toList())
        }
        return Result.parse(serializer.list, json).response()
    }

    override suspend fun edit(t: T) = edit(listOf(t)).first()

    override suspend fun delete(list: Collection<T>): List<T> {
        val json = client.delete<String>("$url/$path") {
            appendHeaders()
            body = RJson.stringify(serializer.list, list.toList())
        }
        return Result.parse(serializer.list, json).response()
    }

    override suspend fun delete(t: T) = delete(listOf(t)).first()

    override suspend fun wipe(list: Collection<T>): List<T> {
        val json = client.post<String>("$url/$path/wipe") {
            appendHeaders()
            body = RJson.stringify(serializer.list, list.toList())
        }
        return Result.parse(serializer.list, json).response()
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
        return Result.parse(serializer.list, json).response()
    }

    override suspend fun load(id: String): T? {
        val json = client.get<String>("$url/$path/$id") {
            appendHeaders()
        }
        return Result.parse(serializer.nullable, json).response()
    }

    override suspend fun page(pi: RestPageRequestInfo): List<T> {
        val json = client.post<String>("$url/$path/page") {
            appendHeaders()
            body = RJson.stringify(RestPageRequestInfo.serializer(), pi)
        }
        return Result.parse(serializer.list, json).response()
    }

    override suspend fun all(): List<T> {
        val json = client.get<String>("$url/$path/all") {
            appendHeaders()
        }
        return Result.parse(serializer.list, json).response()
    }

    override suspend fun allDeleted(): List<T> {
        val json = client.get<String>("$url/$path/all-deleted") {
            appendHeaders()
        }
        return Result.parse(serializer.list, json).response()
    }

    override fun pageLoader(predicate: (T) -> Boolean): PageLoader<*, T> = RestPageLoader(this, predicate)
}