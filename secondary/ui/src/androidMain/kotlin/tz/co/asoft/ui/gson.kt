package tz.co.asoft.ui

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder

val gson = GsonBuilder().apply {
    setLenient()
    val strategy = object : ExclusionStrategy {
        override fun shouldSkipClass(clazz: Class<*>?): Boolean {
            return clazz?.name?.contains("kotlin.jvm.functions") ?: false
        }

        override fun shouldSkipField(f: FieldAttributes?) = false
    }
    addDeserializationExclusionStrategy(strategy)
    addSerializationExclusionStrategy(strategy)
}.create()