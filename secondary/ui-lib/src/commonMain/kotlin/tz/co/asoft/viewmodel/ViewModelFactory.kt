package tz.co.asoft.viewmodel

import tz.co.asoft.platform.core.FragmentActivity
import kotlin.reflect.KClass

expect abstract class ViewModelFactory() {
    actual abstract val a: FragmentActivity
    abstract fun <T : BaseViewModel> create(modelClass: KClass<T>): T
    fun <V : BaseViewModel> viewModel(clazz: KClass<V>): V
}