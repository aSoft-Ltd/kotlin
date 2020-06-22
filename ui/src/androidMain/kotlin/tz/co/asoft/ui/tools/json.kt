package tz.co.asoft.ui.tools

import tz.co.asoft.ui.gson

fun <T> T.toJson() = gson.toJson(this)!!

fun <T> String.fromJson(clazz: Class<T>) = try {
    gson.fromJson(this, clazz) as T
} catch (c: Throwable) {
    null
}