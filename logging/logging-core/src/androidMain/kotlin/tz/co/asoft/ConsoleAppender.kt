package tz.co.asoft

import android.util.Log
import tz.co.asoft.LogLevel.*

actual class ConsoleAppender actual constructor(actual override var options: AppenderOptions) : Appender {

    override fun append(level: LogLevel, msg: String, vararg data: Pair<String, Any?>) {
        val printer: (String, String) -> Unit = when (level) {
            DEBUG -> { tag, txt -> Log.d(tag, txt) }
            INFO -> { tag, txt -> Log.i(tag, txt) }
            WARNING -> { tag, txt -> Log.w(tag, txt) }
            ERROR -> { tag, txt -> Log.e(tag, txt) }
            FAILURE -> { tag, txt -> Log.wtf(tag, txt) }
        }
        printer(options.source, msg)
        if (options.verbose && data.isNotEmpty()) {
            printer(options.source, "= = = = = = = = = = = = D = A = T = A = = = = = = = = = = = =")
            data.forEach {
                printer(options.source, it.first + ": " + it.second)
            }
            printer(options.source, "= ".repeat(31))
        }
    }

    override fun obj(o: Any?) {
        Log.i(options.source, o.toString())
    }

    override fun obj(vararg o: Any?) = o.forEach { obj(it) }
}