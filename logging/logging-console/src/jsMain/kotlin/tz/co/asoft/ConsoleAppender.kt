package tz.co.asoft

import tz.co.asoft.LogLevel.*
import kotlin.js.Console
import kotlin.js.console

actual class ConsoleAppender actual constructor(var options: ConsoleAppenderOptions) : Appender, Console by console {
    override fun append(level: LogLevel, msg: String, vararg data: Pair<String, Any?>) {
        if (level >= options.level) {
            val printer: (Array<out Any?>) -> Unit = when (level) {
                INFO -> { ar -> console.info(*ar) }
                DEBUG -> { ar -> console.log(*ar) }
                WARNING -> { ar -> console.warn(*ar) }
                ERROR -> { ar -> console.error(*ar) }
                FAILURE -> { ar -> console.error(*ar) }
            }

            if (options.verbose) {
                val args = mutableListOf<Any?>("${level.name}: $msg")
                data.forEach {
                    args += "\n${it.first}"
                    args += it.second
                }
                printer(args.toTypedArray())
            } else {
                printer(arrayOf("${level.name}: $msg"))
            }
        }
    }

    override fun append(vararg o: Any?) = console.log(*o)

    fun table(obj: Any) = console.asDynamic().table(obj)
}