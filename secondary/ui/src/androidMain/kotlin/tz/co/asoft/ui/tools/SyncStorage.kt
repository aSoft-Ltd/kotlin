package tz.co.asoft.ui.tools

import android.content.Context

class SyncStorage(ctx: Context, name: String) {
    private val db = ctx.getSharedPreferences(name, Context.MODE_PRIVATE)

    fun get(key: String): String? = db.getString(key, null)

    fun set(key: String, value: String) {
        db.edit().putString(key, value).apply()
    }

    fun remove(key: String) {
        db.edit().remove(key).apply()
    }

    fun clear() {
        db.edit().clear().apply()
    }
}