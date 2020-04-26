package tz.co.asoft.storage

import org.w3c.dom.get
import org.w3c.dom.set
import tz.co.asoft.platform.core.Ctx
import kotlin.browser.window

actual class Storage actual constructor(ctx: Ctx, actual val name: String) {
    private val db = window.localStorage

    private fun getTable(): dynamic {
        var table = db[name]
        if (table == null) {
            table = "{}"
        }
        return JSON.parse(table)
    }

    actual fun get(key: String): String? = getTable()[key].unsafeCast<String?>()

    actual fun set(key: String, value: String) {
        val table = getTable()
        table[key] = value
        db[name] = JSON.stringify(table)
    }

    actual fun remove(key: String) {
        val table = getTable()
        table[key] = undefined
        db[name] = JSON.stringify(table)
    }

    actual fun clear() = db.removeItem(name)
}