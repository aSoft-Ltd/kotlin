package tz.co.asoft

import java.io.File

class FileAppenderOptions(
    val rootDir: File,
    val level: LogLevel = LogLevel.DEBUG
)