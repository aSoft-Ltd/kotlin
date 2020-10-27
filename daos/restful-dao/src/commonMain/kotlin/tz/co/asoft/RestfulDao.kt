package tz.co.asoft

import io.ktor.client.HttpClient
import io.ktor.client.request.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.list
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.builtins.serializer

open class RestfulDao<T : Entity>(
    override val options: RestfulOptions,
    override val serializer: KSerializer<T>,
    override val root: String,
    override val subRoot: String?,
    override val client: HttpClient
) : IRestfulDao<T> {

    fun HttpRequestBuilder.appendHeaders() {
        options.headers.forEach { (k, v) ->
            header(k, v)
        }
    }

    override suspend fun create(list: Collection<T>): List<T> {
        val json = client.post<String>(path) {
            appendHeaders()
            body = RJson.stringify(serializer.list, list.toList())
        }
        return Result.parse(serializer.list, json).response()
    }

    override suspend fun create(t: T) = create(listOf(t)).first()

    override suspend fun edit(list: Collection<T>): List<T> {
        val json = client.patch<String>(path) {
            appendHeaders()
            body = RJson.stringify(serializer.list, list.toList())
        }
        return Result.parse(serializer.list, json).response()
    }

    override suspend fun edit(t: T) = edit(listOf(t)).first()

    override suspend fun delete(list: Collection<T>): List<T> {
        val json = client.delete<String>(path) {
            appendHeaders()
            body = RJson.stringify(serializer.list, list.toList())
        }
        return Result.parse(serializer.list, json).response()
    }

    override suspend fun delete(t: T) = delete(listOf(t)).first()

    override suspend fun wipe(list: Collection<T>): List<T> {
        val json = client.delete<String>("$path/wipe") {
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
        val json = client.post<String>("$path/load") {
            appendHeaders()
            body = RJson.stringify(String.serializer().list, stringIds)
        }
        return Result.parse(serializer.list, json).response()
    }

    override suspend fun load(id: String): T? {
        val json = client.get<String>("$path/$id") {
            appendHeaders()
        }
        return Result.parse(serializer.nullable, json).response()
    }

    override suspend fun load(startAt: String?, limit: Int): List<T> {
        var query = "size=$limit"
        if (startAt != null) {
            query += "&startAt=$startAt"
        }
        val json = client.get<String>("$path/page?$query") {
            appendHeaders()
        }
        return Result.parse(serializer.list, json).response()
    }

    override suspend fun all(): List<T> {
        val json = client.get<String>("$path/all") {
            appendHeaders()
        }
        return Result.parse(serializer.list, json).response()
    }

    override suspend fun allDeleted(): List<T> {
        val json = client.get<String>("$path/all-deleted") {
            appendHeaders()
        }
        return Result.parse(serializer.list, json).response()
    }

    override fun pageLoader(predicate: (T) -> Boolean): PageLoader<*, T> = RestfulPageLoader(this, predicate)
}