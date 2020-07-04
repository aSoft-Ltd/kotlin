package tz.co.asoft.persist.dao

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import tz.co.asoft.persist.model.Entity

interface IDao<T : Entity> {

    suspend fun create(list: Collection<T>): List<T> = coroutineScope {
        list.map { async { create(it) } }.mapNotNull { it.await() }
    }

    suspend fun create(t: T): T

    suspend fun edit(list: Collection<T>): List<T> = coroutineScope {
        list.map { async { edit(it) } }.mapNotNull { it.await() }
    }

    suspend fun edit(t: T): T

    suspend fun delete(list: Collection<T>): List<T> = coroutineScope {
        list.map { async { delete(it) } }.mapNotNull { it.await() }
    }

    suspend fun delete(t: T): T = edit(t.apply { deleted = true })

    suspend fun wipe(list: Collection<T>): List<T> = coroutineScope {
        list.map { async { wipe(it) } }.mapNotNull { it.await() }
    }

    suspend fun wipe(t: T): T

    suspend fun load(ids: Collection<Any>): List<T> = coroutineScope {
        ids.toSet().map { async { load(it.toString()) } }.mapNotNull { it.await() }
    }

    suspend fun load(id: Number): T? = load(id.toString())

    suspend fun load(id: String): T?

    suspend fun all(): List<T>

    suspend fun allDeleted(): List<T>

    suspend fun paged(pageNumber: Int = 1, pageSize: Int = 25): List<T>
}