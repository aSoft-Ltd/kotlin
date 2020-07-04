package tz.co.asoft.persist.repo

import tz.co.asoft.persist.dao.ICache
import tz.co.asoft.persist.dao.IDao
import tz.co.asoft.persist.model.Entity

open class TwinRepo<T : Entity>(override val localDao: ICache<T>, override val remoteDao: IDao<T>) :
    ITwinRepo<T> {
    override suspend fun paged(pageNumber: Int, pageSize: Int): List<T> {
        return remoteDao.paged(pageNumber, pageSize)
    }
}
