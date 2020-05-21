package tz.co.asoft.platform.core

@Deprecated("There is no context in jvm desktop")
actual abstract class Ctx

actual fun Ctx.alert(msg: Any?) {
    println(msg)
}