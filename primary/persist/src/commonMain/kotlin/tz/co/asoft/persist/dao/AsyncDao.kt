package tz.co.asoft.persist.dao

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

open class AsyncDao<T : Any>(vararg individualDaos: IDao<out T>) : IDao<T> {

    private val daos: List<IDao<T>> = individualDaos.map { it as IDao<T> }

    override suspend fun create(list: Collection<T>): List<T> = coroutineScope {
        daos.map { async { it.create(list) } }.map { it.await() }.flatten()
    }

    override suspend fun create(t: T): T = coroutineScope {
        daos.map { async { it.create(t) } }.map { it.await() }.first()
    }

    override suspend fun edit(list: Collection<T>): List<T> = coroutineScope {
        daos.map { async { it.edit(list) } }.map { it.await() }.flatten()
    }

    override suspend fun edit(t: T): T = coroutineScope {
        daos.map { async { it.edit(t) } }.map { it.await() }.first()
    }

    override suspend fun delete(list: Collection<T>): List<T> = coroutineScope {
        daos.map { async { it.delete(list) } }.map { it.await() }.flatten()
    }

    override suspend fun delete(t: T): T = coroutineScope {
        daos.map { async { it.delete(t) } }.map { it.await() }.first()
    }

    override suspend fun wipe(list: Collection<T>): List<T> = coroutineScope {
        daos.map { async { it.wipe(list) } }.map { it.await() }.flatten()
    }

    override suspend fun wipe(t: T): T = coroutineScope {
        daos.map { async { it.wipe(t) } }.map { it.await() }.first()
    }

    override suspend fun load(ids: Collection<Any>): List<T> = coroutineScope {
        daos.map { async { it.load(ids) } }.map { it.await() }.flatten()
    }

    override suspend fun load(id: String): T? = coroutineScope {
        daos.map { async { it.load(id) } }.mapNotNull { it.await() }.firstOrNull()
    }

    override suspend fun all(): List<T> = coroutineScope {
        daos.map { async { it.all() } }.map { it.await() }.flatten()
    }
}