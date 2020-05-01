package tz.co.asoft.deployment.target

import kotlin.reflect.KProperty

class TargetDeligate(private val values: MutableMap<String, Any>) {
    operator fun getValue(thisRef: Nothing?, property: KProperty<*>): Target {
        val map = mutableMapOf<String, Any>().apply {
            put("name", property.name)
            putAll(this@TargetDeligate.values)
        }
        return Target(property.name, map)
    }

    fun join(vararg t: Target): TargetDeligate {
        t.forEach {
            it.values.forEach { (k, v) ->
                if (k != "name")
                    values.putIfAbsent(k, v)
            }
        }
        return this
    }
}