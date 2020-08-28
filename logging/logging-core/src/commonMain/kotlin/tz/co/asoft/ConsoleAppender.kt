package tz.co.asoft

expect class ConsoleAppender(options: AppenderOptions = AppenderOptions()) : Appender {
    override var options: AppenderOptions
}