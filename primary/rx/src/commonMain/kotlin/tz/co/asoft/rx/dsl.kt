package tz.co.asoft.rx

import kotlinx.coroutines.CoroutineScope

fun <T> CoroutineScope.observe(l: LiveData<T>, onChange: (T) -> Unit) = l.onChange(this, onChange)

inline fun <T, S> LiveData<T>.map(transform: (value: T) -> S): LiveData<S> = LiveData(transform(value))