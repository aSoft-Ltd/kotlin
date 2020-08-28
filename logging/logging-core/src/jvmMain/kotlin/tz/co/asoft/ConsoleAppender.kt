package tz.co.asoft

actual class ConsoleAppender actual constructor(actual override var options: AppenderOptions) : Appender {
    private val goodPrinter: (String) -> Unit = System.out::println
    private val badPrinter: (String) -> Unit = System.err::println
    override fun append(level: LogLevel, msg: String, vararg data: Pair<String, Any?>) {
        val printer = if (level.ordinal >= 3) badPrinter else goodPrinter
        if (options.verbose) {
            printer("= ".repeat(31))
            printer(msg)
            printer("= = = = = = = = = = = = D = A = T = A = = = = = = = = = = = =")
            printer("Source: ${options.source}")
            printer("Level: ${level.name}")
            data.forEach {
                printer("${it.first}: ${it.second}")
            }
            printer("= ".repeat(31))
        } else {
            printer("${level.name}: $msg")
        }
    }
    
    override fun obj(o: Any?) {
        println(o)
    }

    override fun obj(vararg o: Any?) = o.forEach { obj(it) }
}