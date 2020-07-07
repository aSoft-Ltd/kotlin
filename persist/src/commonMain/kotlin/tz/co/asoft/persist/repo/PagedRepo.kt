package tz.co.asoft.persist.repo

import tz.co.asoft.persist.dao.PagedDao
import tz.co.asoft.persist.model.Entity

abstract class PagedRepo<K : Any, T : Entity>(val dao: PagedDao<K, T>) : PagedDao<K, T>() {
    override suspend fun load(params: LoadParams<K>) = dao.load(params)
}