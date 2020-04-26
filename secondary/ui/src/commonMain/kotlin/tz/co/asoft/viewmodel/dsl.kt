@file:JvmName("CommonDsl")

package tz.co.asoft.viewmodel

import kotlinx.atomicfu.atomic
import tz.co.asoft.platform.core.Activity
import tz.co.asoft.platform.core.Ctx
import kotlin.jvm.JvmName
import kotlin.reflect.KClass

@PublishedApi
internal val viewModelFactories = atomic(mutableMapOf<KClass<*>, ViewModelFactory>())

inline fun <reified V : ViewModelFactory> vmFactory(builder: () -> V): V {
    return viewModelFactories.value.getOrPut(V::class, builder) as V
}

expect val Activity.appCtx: Ctx