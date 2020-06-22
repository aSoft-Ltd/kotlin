package tz.co.asoft.rx

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun <T> CoroutineScope.observe(l: LiveData<T>, onChange: (T) -> Unit) = l.onChange(this, onChange)

fun <T, S> LiveData<T>.map(scope: CoroutineScope, transform: (T) -> S): LiveData<S> {
    val newLiveData = LiveData(transform(value))
    onChange(scope) {
        newLiveData.value = transform(it)
    }
    return newLiveData
}

fun <T, S> LiveData<T>.map(scope: CoroutineScope, default: S, transform: suspend (T) -> S): LiveData<S> {
    val newLiveData = LiveData(default)
    onChange(scope) {
        scope.launch {
            newLiveData.value = transform(it)
        }
    }
    return newLiveData
}