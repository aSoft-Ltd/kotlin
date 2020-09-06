package tz.co.asoft

import java.io.File

class FileAppender(var options: FileAppenderOptions) : Appender {
    init {
        options.rootDir.also { if (!it.exists()) it.mkdirs() }
    }

    private val dir get() = File(options.rootDir, DateTime.nowLocal().format("yyyy/MM/dd")).also { if (!it.exists()) it.mkdirs() }

    private val file get() = File(dir, DateTime.nowLocal().format("hh") + ".log").also { if (!it.exists()) it.createNewFile() }

    override fun append(level: LogLevel, msg: String, vararg data: Pair<String, Any?>) {
        if (level > options.level) file.apply {
            appendText("---->\n")
            appendText("${level.name}: $msg\n")
            data.forEach {
                appendText("${it.first}: ${it.second.toString()}\n")
            }
        }
    }

    override fun append(vararg o: Any?) = o.forEach { append(LogLevel.DEBUG, msg = it.toString()) }
}