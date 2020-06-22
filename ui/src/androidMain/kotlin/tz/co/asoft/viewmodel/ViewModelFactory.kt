package tz.co.asoft.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import tz.co.asoft.platform.core.FragmentActivity
import kotlin.reflect.KClass

actual abstract class ViewModelFactory : ViewModelProvider.Factory {
    actual abstract val a: FragmentActivity
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T = create(modelClass.kotlin)
    actual abstract fun <T : BaseViewModel> create(modelClass: KClass<T>): T
    actual fun <V : BaseViewModel> viewModel(clazz: KClass<V>): V {
        return ViewModelProviders.of(a, this).get(clazz.java)
    }
}