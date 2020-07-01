package tz.co.asoft.persist.dao

import tz.co.asoft.persist.model.Entity
import kotlin.reflect.KClass

open class MultiDao<T : Entity>(
    vararg individualDaos: Pair<KClass<out T>, IDao<out T>>
) : IMultiDao<T> {
    override val daos = individualDaos.toMap().mapValues { (_, ds) -> ds as IDao<T> }.toMap()
}