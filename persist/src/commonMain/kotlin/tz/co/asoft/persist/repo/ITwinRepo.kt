package tz.co.asoft.persist.repo

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import tz.co.asoft.persist.dao.IDao
import tz.co.asoft.persist.model.Entity

interface ITwinRepo<T : Entity> : IRepo<T> {
    val remoteDao: IDao<T>
    val localDao: IDao<T>

    override suspend fun create(t: T) = remoteDao.create(t).also { localDao.create(it) }

    override suspend fun edit(t: T) = remoteDao.edit(t).also { localDao.edit(it) }

    override suspend fun delete(t: T) = remoteDao.delete(t).also { localDao.delete(it) }

    override suspend fun wipe(t: T) = remoteDao.wipe(t).also { localDao.wipe(t) }

    fun loadFlowing(id: String) = flow {
        coroutineScope {
            val local = async { localDao.load(id) }
            val remote = async { remoteDao.load(id) }
            local.await()?.let { emit(it) }
            remote.await().let {
                emit(it)
                if (it != null) localDao.create(it)
            }
        }
    }

    fun loadFlowing(ids: Collection<Any>) = flow {
        coroutineScope {
            val local = async { localDao.load(ids) }
            val remote = async { remoteDao.load(ids) }
            emit(local.await())
            emit(remote.await())
        }
    }

    override suspend fun load(id: String) = loadFlowing(id).toList().last()

    override suspend fun allDeleted() = remoteDao.allDeleted()

    override suspend fun all() = localDao.all().apply {
        remoteDao.all().let { localDao.create(it) }
    }

    fun allFlowing() = flow {
        coroutineScope {
            val local = async { localDao.all() }
            val remote = async { remoteDao.all() }
            val localRes = local.await()
            if (localRes.isNotEmpty()) {
                emit(localRes)
            }
            remote.await().let {
                emit(it)
                localDao.create(it)
            }
        }
    }
}