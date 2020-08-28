package tz.co.asoft

open class AppenderOptions(
    val source: String = "global",
    val level: LogLevel = LogLevel.DEBUG,
    val verbose: Boolean = true
)