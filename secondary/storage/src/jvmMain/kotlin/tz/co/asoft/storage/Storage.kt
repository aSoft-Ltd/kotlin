package tz.co.asoft.storage

import tz.co.asoft.platform.core.Ctx

actual class Storage actual constructor(ctx: Ctx, actual val name: String) {
    actual fun get(key: String): String? = null

    actual fun set(key: String, value: String) {}

    actual fun remove(key: String) {}

    actual fun clear() {}
}