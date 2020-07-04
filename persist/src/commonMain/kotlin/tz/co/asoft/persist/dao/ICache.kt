package tz.co.asoft.persist.dao

import tz.co.asoft.persist.model.Entity

interface ICache<T : Entity> : IDao<T> {
    var data: MutableMap<String, T>?
    override suspend fun create(list: Collection<T>): List<T> {
        if (data == null) {
            data = mutableMapOf()
        }
        data?.putAll(list.associateBy { it.uid })
        return list.toList()
    }

    override suspend fun create(t: T) = create(listOf(t)).first()

    override suspend fun edit(t: T) = create(t)

    override suspend fun wipe(t: T) = data?.remove(t.uid) ?: t

    override suspend fun load(id: String) = data?.get(id)

    override suspend fun load(ids: Collection<Any>) = ids.mapNotNull { data?.get(it) }

    override suspend fun all(): List<T> = data?.values?.toList() ?: listOf()

    override suspend fun allDeleted(): List<T> = all().filter { it.deleted }

    override suspend fun paged(pageNumber: Int, pageSize: Int): List<T>
}