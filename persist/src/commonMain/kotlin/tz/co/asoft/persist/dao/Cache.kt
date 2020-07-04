package tz.co.asoft.persist.dao

import tz.co.asoft.persist.model.Entity

open class Cache<T : Entity> : ICache<T> {
    override var data: MutableMap<String, T>? = null
    override suspend fun paged(pageNumber: Int, pageSize: Int): List<T> =
        data?.values?.chunked(pageSize)?.get(pageNumber) ?: listOf()
}