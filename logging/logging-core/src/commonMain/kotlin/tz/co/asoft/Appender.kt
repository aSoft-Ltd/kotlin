package tz.co.asoft

interface Appender {
    var options: AppenderOptions
    fun append(level: LogLevel, msg: String, vararg data: Pair<String, Any?>)
    fun obj(vararg o: Any?)
    fun obj(o: Any?)
}