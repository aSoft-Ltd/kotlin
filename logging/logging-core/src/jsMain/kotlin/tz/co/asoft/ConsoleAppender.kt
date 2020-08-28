package tz.co.asoft

import kotlin.js.Console

actual class ConsoleAppender actual constructor(actual override var options: AppenderOptions) :
    Appender, Console by console {
    override fun append(level: LogLevel, msg: String, vararg data: Pair<String, Any?>) {
        val printer: (Array<out Any?>) -> Unit = when (level) {
            LogLevel.INFO -> { ar -> console.info(*ar) }
            LogLevel.DEBUG -> { ar -> console.log(*ar) }
            LogLevel.WARNING -> { ar -> console.warn(*ar) }
            LogLevel.ERROR -> { ar -> console.error(*ar) }
            LogLevel.FAILURE -> { ar -> console.error(*ar) }
        }
        if (options.verbose) {
            val args = mutableListOf<Any?>("${level.name}: $msg\nSource: ${options.source}")
            data.forEach {
                args += "\n${it.first}"
                args += it.second
            }
            printer(args.toTypedArray())
        } else {
            printer(arrayOf("${level.name}: $msg"))
        }
    }

    fun table(obj: Any) = console.asDynamic().table(obj)

    override fun obj(o: Any?) = console.log(o)

    override fun obj(vararg o: Any?) = console.log(*o)
}