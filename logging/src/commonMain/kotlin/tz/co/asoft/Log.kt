package tz.co.asoft

import kotlinx.serialization.Serializable

@Serializable
data class Log(
    var level: String = Level.DEBUG.name,
    var msg: String = "",
    var source: String = "anonymous"
) : Entity {
    override var uid: String? = null
    override var deleted = false

    var time = DateTime.nowUnixLong()

    private val logger get() = Logger(source)

    enum class Level {
        ERROR, WARNING, DEBUG, FAILURE, INFO
    }

    override fun toString() = "${time.asFormatedDate()} ${levelString()} $source - $msg"

    private fun levelString() = when (level) {
        Level.ERROR.name -> "[ ERROR ]"
        Level.WARNING.name -> "[WARNING]"
        Level.DEBUG.name -> "[ DEBUG ]"
        Level.FAILURE.name -> "[FAILURE]"
        else -> "[I N F O]"
    }

    fun log() = when (level) {
        Level.ERROR.name -> logger.e(msg)
        Level.WARNING.name -> logger.w(msg)
        Level.DEBUG.name -> logger.d(msg)
        Level.FAILURE.name -> logger.f(msg)
        else -> logger.i(msg)
    }
}
