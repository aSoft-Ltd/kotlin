package tz.co.asoft

import kotlinx.atomicfu.AtomicRef
import kotlinx.atomicfu.atomic

abstract class DaoFactory<T : Any> {
    private val daoConfig: AtomicRef<T?> = atomic(null)
    val dao get() = daoConfig.value ?: throw Exception("Dao ${this::class.simpleName} is not yet configured")
    fun init(c: T) {
        daoConfig.value = c
    }
}