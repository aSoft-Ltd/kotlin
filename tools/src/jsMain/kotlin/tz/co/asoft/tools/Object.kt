package tz.co.asoft.tools

import kotlinext.js.Object
import kotlinext.js.jsObject

inline fun <T, S> Object.assign(target: T, source: S): T {
    return asDynamic().assign(target, source).unsafeCast<T>()
}