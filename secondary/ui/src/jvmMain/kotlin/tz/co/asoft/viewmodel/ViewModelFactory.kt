package tz.co.asoft.viewmodel

import tz.co.asoft.platform.core.FragmentActivity
import kotlin.reflect.KClass

actual abstract class ViewModelFactory {
    actual abstract val a: FragmentActivity
    actual abstract fun <T : BaseViewModel> create(modelClass: KClass<T>): T

    actual fun <V : BaseViewModel> viewModel(clazz: KClass<V>): V {
        val vm = viewModels.value[clazz] as? V
        return if (vm != null) {
            vm
        } else {
            viewModels.value[clazz] = create(clazz)
            viewModels.value[clazz] as V
        }
    }
}