package tz.co.asoft

fun Appender.d(msg: String, vararg data: Pair<String, Any?>) = append(LogLevel.DEBUG, msg, *data)
fun Appender.i(msg: String, vararg data: Pair<String, Any?>) = append(LogLevel.INFO, msg, *data)
fun Appender.w(msg: String, vararg data: Pair<String, Any?>) =
    append(LogLevel.WARNING, msg, *data)
fun Appender.e(msg: String, c: Throwable?, vararg data: Pair<String, Any?>) =
    append(LogLevel.ERROR, msg, *data)
fun Appender.f(msg: String, c: Throwable?, vararg data: Pair<String, Any?>) =
    append(LogLevel.FAILURE, msg, *data)

fun Appender.e(msg: String, vararg data: Pair<String, Any?>) = e(msg, null, *data)
fun Appender.e(c: Throwable?) = e(c?.message ?: "Unknown Error", c)

fun Appender.f(msg: String, vararg data: Pair<String, Any?>) = f(msg, null, *data)
fun Appender.f(c: Throwable?) = f(c?.message ?: "Unknown Error", c)