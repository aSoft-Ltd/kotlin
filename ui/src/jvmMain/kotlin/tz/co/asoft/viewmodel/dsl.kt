package tz.co.asoft.viewmodel

import kotlinx.atomicfu.atomic
import tz.co.asoft.platform.core.Activity
import tz.co.asoft.platform.core.Ctx
import tz.co.asoft.platform.core.FragmentActivity
import kotlin.reflect.KClass

@PublishedApi
internal val viewModels = atomic(mutableMapOf<KClass<*>, BaseViewModel>())

actual val Activity.appCtx: Ctx get() = this