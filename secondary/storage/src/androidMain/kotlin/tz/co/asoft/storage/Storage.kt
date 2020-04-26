package tz.co.asoft.storage

import android.content.Context
import tz.co.asoft.platform.core.Ctx

actual class Storage actual constructor(ctx: Ctx, actual val name: String) {
    private val db = ctx.getSharedPreferences(name, Context.MODE_PRIVATE)

    actual fun get(key: String): String? = db.getString(key, null)

    actual fun set(key: String, value: String) = db.edit().putString(key, value).apply()

    actual fun remove(key: String) = db.edit().remove(key).apply()

    actual fun clear() = db.edit().clear().apply()
}