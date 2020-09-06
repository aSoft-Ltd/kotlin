package tz.co.asoft

actual class ConsoleAppender actual constructor(var options: ConsoleAppenderOptions) : Appender {
    override fun append(level: LogLevel, msg: String, vararg data: Pair<String, Any?>) {
        if (level >= options.level) {
            val printer: (String) -> Unit = if (level >= LogLevel.ERROR) System.err::println else System.out::println
            if (options.verbose) {
                printer("\n" + "= ".repeat(31))
                printer("${level.name}: $msg")
                data.forEach {
                    printer("${it.first}: ${it.second}")
                }
                printer("= ".repeat(31))
            } else {
                printer("${level.name}: $msg")
            }
        }
    }

    override fun append(vararg o: Any?) = o.forEach { println(it) }
}