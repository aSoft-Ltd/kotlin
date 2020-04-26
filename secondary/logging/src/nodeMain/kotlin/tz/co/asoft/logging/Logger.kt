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
        Color.Blue.println(log)
        log.send()
    }

    actual fun e(msg: String, c: Cause?) {
        val log = Log(Log.Level.ERROR.name, msg, origin)
        Color.Red.println(log)
        c?.printStackTrace()
        log.send()
    }

    actual fun e(c: Cause?) {
        val log = Log(Log.Level.ERROR.name, c?.message ?: "No Message", origin)
        Color.Red.println(log)
        c?.printStackTrace()
        log.send()
    }

    actual fun f(msg: String, c: Cause?) {
        val log = Log(Log.Level.FAILURE.name, msg, origin)
        Color.Red.println(log)
        c?.printStackTrace()
        log.send()
    }

    actual fun f(c: Cause?) {
        val log = Log(Log.Level.FAILURE.name, c?.message ?: "No Message", origin)
        Color.Red.println(log)
        c?.printStackTrace()
        log.send()
    }

    actual fun w(msg: String) {
        val log = Log(Log.Level.WARNING.name, msg, origin)
        Color.Yellow.println(log)
        log.send()
    }

    actual fun i(msg: String) {
        val log = Log(Log.Level.INFO.name, msg, origin)
        Color.Normal.println(log)
        log.send()
    }

    actual fun obj(vararg o: Any?) {
        o.forEach {
            obj(it)
        }
    }

    actual fun obj(o: Any?) = console.log(o)

    private fun Log.send() = GlobalScope.launch {
        repo?.create(this@send)
    }

    private fun Color.println(log: Log) {
        console.warn("$escape$log\u001B[0m")
    }

    private fun Color.trace(obj: Any?) {
        console.warn(escape)
        console.error(obj)
        console.error("\nCurrent Call Stack\n")
        console.asDynamic().trace(obj)
        console.warn("\u001B[0m")
    }

    private fun Cause.printStackTrace() {
        val err = Error(this)
        Color.Red.trace(this)
    }
}