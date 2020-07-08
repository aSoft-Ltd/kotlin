package tz.co.asoft.paging.dao

import tz.co.asoft.paging.Page
import tz.co.asoft.paging.PageFetcher
import tz.co.asoft.paging.PageRequestInfo
import tz.co.asoft.paging.Pager
import tz.co.asoft.persist.dao.IDao
import tz.co.asoft.persist.model.Entity

interface Pageable<K : Any, D : Entity> {
    suspend fun load(info: PageRequestInfo<K>): Page<K, D>
    fun pager(pageSize: Int, extraPages: Int = 1, showTmpPages: Boolean = true): Pager<*, D> {
        val fetcher = PageFetcher(this, pageSize, extraPages)
        return Pager(fetcher, showTmpPages)
    }
}