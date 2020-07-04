package tz.co.asoft.persist.dao

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import tz.co.asoft.persist.model.Entity
import kotlin.reflect.KClass

open class MultiDao<T : Entity>(
    vararg individualDaos: Pair<KClass<out T>, IDao<out T>>
) : IMultiDao<T> {
    override val daos = individualDaos.toMap().mapValues { (_, ds) -> ds as IDao<T> }.toMap()

    override suspend fun paged(pageNumber: Int, pageSize: Int): List<T> = coroutineScope {
        daos.values.map {
            async { it.paged(pageNumber, pageSize) }
        }.awaitAll().flatten()
    }
}