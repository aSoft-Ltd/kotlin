package tz.co.asoft.persist.repo

import tz.co.asoft.persist.dao.ICache
import tz.co.asoft.persist.dao.IDao
import tz.co.asoft.persist.model.Entity

open class CachedRepo<T : Entity>(override val cache: ICache<T>, override val dao: IDao<T>) :
    ICachedRepo<T> {
    override suspend fun paged(pageNumber: Int, pageSize: Int): List<T> =
        dao.paged(pageNumber, pageSize)
}