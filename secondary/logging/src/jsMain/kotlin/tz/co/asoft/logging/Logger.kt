package tz.co.asoft.logging

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tz.co.asoft.logging.tools.Cause
import tz.co.asoft.persist.repo.IRepo

actual open class Logger actual constructor(protected actual val source: String, protected actual val repo: IRepo<Log>?) {
    actual var tag = ""

    private val origin get() = if (tag.isEmpty()) source else "$source/$tag"

    actual fun d(msg: String) {
        val log = Log(Log.Level.DEBUG.name, msg, origin)
        console.log("$log")
        log.send()
    }

    actual fun e(msg: String, c: Cause?) {
        val log = Log(Log.Level.ERROR.name, msg, origin)
        console.error("$log")
        log.send()
    }

    actual fun e(c: Cause?) {
        val log = Log(Log.Level.ERROR.name, c?.message ?: "No Message", origin)
        console.error("$log")
        log.send()
    }

    actual fun f(msg: String, c: Cause?) {
        val log = Log(Log.Level.FAILURE.name, msg, origin)
        console.error("$log")
        log.send()
    }

    actual fun f(c: Cause?) {
        val log = Log(Log.Level.FAILURE.name, c?.message ?: "No Message", origin)
        console.error("$log")
        log.send()
    }

    actual fun w(msg: String) {
        val log = Log(Log.Level.WARNING.name, msg, origin)
        console.warn("$log")
        log.send()
    }

    actual fun i(msg: String) {
        val log = Log(Log.Level.INFO.name, msg, origin)
        console.info("$log")
        log.send()
    }

    actual fun obj(vararg o: Any?) {
        console.log(*o)
    }

    actual fun obj(o: Any?) = if (o is Collection<*>) {
        console.log(o.toTypedArray())
    } else {
        console.log(o)
    }

    fun dir(o: Any) = console.dir(o)

    private fun Log.send() = GlobalScope.launch {
        repo?.create(this@send)
    }
}