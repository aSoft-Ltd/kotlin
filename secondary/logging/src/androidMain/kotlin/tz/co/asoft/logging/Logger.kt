package tz.co.asoft.logging

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tz.co.asoft.logging.tools.Cause
import tz.co.asoft.persist.repo.IRepo
import android.util.Log as ALog

actual open class Logger actual constructor(protected actual val source: String, protected actual val repo: IRepo<Log>?) {
    actual var tag = ""

    private val origin get() = if (tag.isEmpty()) source else "$source/$tag"

    actual fun d(msg: String) {
        val log = Log(Log.Level.DEBUG.name, msg, origin)
        ALog.d(source, log.msg)
        log.send()
    }

    actual fun e(msg: String, c: Cause?) {
        val log = Log(Log.Level.ERROR.name, msg, origin)
        ALog.e(source, log.msg, c)
        log.send()
    }

    actual fun e(c: Cause?) {
        val log = Log(Log.Level.ERROR.name, c?.message ?: "No Message", origin)
        ALog.e(source, log.msg, c)
        log.send()
    }

    actual fun f(msg: String, c: Cause?) {
        val log = Log(Log.Level.FAILURE.name, msg, origin)
        ALog.e(source, msg, c)
        log.send()
    }

    actual fun f(c: Cause?) {
        val log = Log(Log.Level.FAILURE.name, c?.message ?: "No Message", origin)
        ALog.e(source, log.msg, c)
        log.send()
    }

    actual fun w(msg: String) {
        val log = Log(Log.Level.WARNING.name, msg, origin)
        ALog.w(source, log.msg)
        log.send()
    }

    actual fun i(msg: String) {
        val log = Log(Log.Level.INFO.name, msg, origin)
        ALog.i(source, log.msg)
        log.send()
    }

    actual fun obj(vararg o: Any?) {
        o.forEach {
            ALog.i(source, it.toString())
        }
    }

    actual fun obj(o: Any?) {
        ALog.i(source, o.toString())
    }

    private fun Log.send() = GlobalScope.launch {
        repo?.create(this@send)
    }
}