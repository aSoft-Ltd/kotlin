package tz.co.asoft

import kotlinx.atomicfu.AtomicRef
import kotlinx.atomicfu.atomic

@Deprecated("Use better replacement", ReplaceWith("DaoFactory"))
abstract class DaoFactoryConfig<T : Any> {
    private val daoConfig: AtomicRef<T?> = atomic(null)
    val dao get() = daoConfig.value ?: throw Exception("Dao Factory is not yet configured")
    fun config(c: T) {
        daoConfig.value = c
    }
}