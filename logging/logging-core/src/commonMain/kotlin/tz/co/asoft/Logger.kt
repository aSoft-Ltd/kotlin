package tz.co.asoft

class Logger(vararg appenders: Appender) : Appender {
    private val appenders = appenders.toList()

    override var options = AppenderOptions()
        set(value) {
            appenders.forEach { it.options = value }
            field = value
        }

    override fun append(level: LogLevel, msg: String, vararg data: Pair<String, Any?>) {
        appenders.forEach { it.append(level, msg, *data) }
    }

    override fun obj(vararg o: Any?) = appenders.forEach { it.obj(*o) }

    override fun obj(o: Any?) = appenders.forEach { it.obj(o) }
}