package tz.co.asoft.ui.tools

import kotlinext.js.Object
import kotlinext.js.jsObject

inline fun <T> Object.assign(target: dynamic, source: dynamic): T {
    return asDynamic().assign(target, source).unsafeCast<T>()
}

fun <T : Any> T.newJsObject(): T {
    val obj = jsObject<T> { }
    return Object.assign(obj, this)
}