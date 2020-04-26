package tz.co.asoft.components.android

import android.os.Bundle
import android.util.Log
import tz.co.asoft.persist.tools.Cause
import tz.co.asoft.ui.gson
import kotlin.reflect.KClass

fun <T> Bundle.load(entity: String): T? = try {
    val clazz = Class.forName(getString("${entity}Class")!!) as Class<T>
    gson.fromJson(getString(entity)!!, clazz)
} catch (c: Cause) {
    Log.d("asoft-ui", "Failed to load $entity: ${c.message}")
    null
}

inline fun <reified T> Bundle.append(entity: String, value: T) {
    putString("${entity}Class", T::class.java.name)
    putString(entity, gson.toJson(value))
}

fun <T : Any> Bundle.append(entity: String, clazz: KClass<out T>, value: T) {
    putString("${entity}Class", clazz.java.name)
    putString(entity, gson.toJson(value))
}