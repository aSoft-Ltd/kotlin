package tz.co.asoft

interface IDao<T : Entity> {

    suspend fun create(list: Collection<T>): List<T>

    suspend fun create(t: T): T

    suspend fun edit(list: Collection<T>): List<T>

    suspend fun edit(t: T): T

    suspend fun delete(list: Collection<T>): List<T>

    suspend fun delete(t: T): T

    suspend fun wipe(list: Collection<T>): List<T>

    suspend fun wipe(t: T): T

    suspend fun load(ids: Collection<Any>): List<T>

    suspend fun load(id: Number): T? = load(id.toString())

    suspend fun load(id: String): T?

    suspend fun all(): List<T>

    suspend fun allDeleted(): List<T>

    suspend fun load(startAt: String?, limit: Int): List<T>

    fun pageLoader(predicate: (T) -> Boolean = { !it.deleted }): PageLoader<*, T>
}