package tz.co.asoft.logging

import tz.co.asoft.klock.DateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import tz.co.asoft.persist.model.Entity

@Serializable
open class Log(
        var level: String = Level.DEBUG.name,
        var msg: String = "",
        var source: String = "anonymous"
) : Entity {
    override var uid = ""

    var time = DateTime.nowUnixLong()

    @Transient
    private val logger
        get() = Logger(source)

    enum class Level {
        ERROR, WARNING, DEBUG, FAILURE, INFO
    }

    override fun toString() = DateTime.fromUnix(time).local.format("yyyy-MM-dd HH:mm:ss.SSS") + " ${levelString()} $source - $msg"

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
