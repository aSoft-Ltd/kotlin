package tz.co.asoft.storage

import tz.co.asoft.platform.core.Ctx

expect class Storage(ctx: Ctx, name: String) {
    val name: String
    fun get(key: String): String?
    fun set(key: String, value: String)
    fun remove(key: String)
    fun clear()
}