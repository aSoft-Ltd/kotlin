package tz.co.asoft

val Console by lazy { ConsoleAppender() }

inline fun <reified T> T.logger(
    source: String = T::class.simpleName ?: "global",
    level: LogLevel = LogLevel.DEBUG,
    verbose: Boolean = true
) = Logger(ConsoleAppender(AppenderOptions(source, level, verbose)))

inline fun <reified T> T.logger(options: AppenderOptions) = Logger(ConsoleAppender(options))
