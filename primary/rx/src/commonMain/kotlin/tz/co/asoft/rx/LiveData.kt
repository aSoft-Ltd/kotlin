package tz.co.asoft.rx

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.isActive
import kotlin.properties.Delegates

open class LiveData<T>(initialValue: T) {
    var value: T = initialValue
        set(value) {
            field = value
            dispatch(value)
        }

    internal var handlers = mutableMapOf<CoroutineScope, MutableList<(T) -> Unit>>()
        get() {
            field = field.filterKeys { it.isActive }.toMutableMap()
            return field
        }

    fun dispatch(newValue: T = value) {
        handlers.values.flatten().forEach { it(newValue) }
    }

    fun onChange(scope: CoroutineScope, action: (T) -> Unit) {
        if (scope.isActive) {
            handlers.getOrPut(scope) { mutableListOf() }.add(action)
            action(value)
        }
    }
}