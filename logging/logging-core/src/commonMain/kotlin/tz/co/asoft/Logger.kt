package tz.co.asoft

class Logger(vararg appenders: Appender) : Appender {
    companion object {
        fun of(source: String): Logger = Logger(*Logging.appenders.toTypedArray()).apply {
            extra["source"] = source
        }

        fun of(vararg data: Pair<String, Any?>) = Logger(*Logging.appenders.toTypedArray()).apply {
            extra.putAll(data.toMap())
        }
    }

    private val appenders = appenders.toList()

    internal val extra = mutableMapOf<String, Any?>()

    private inline fun Map<String, Any?>.toArray() = map { (k, v) -> k to v }

    override fun append(level: LogLevel, msg: String, vararg data: Pair<String, Any?>) {
        appenders.forEach { it.append(level, msg, *(extra.toArray() + data.toList()).toTypedArray()) }
    }

    override fun append(vararg o: Any?) = appenders.forEach { it.append(o) }
}