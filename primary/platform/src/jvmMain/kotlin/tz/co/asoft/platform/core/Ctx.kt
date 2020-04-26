package tz.co.asoft.platform.core

actual abstract class Ctx

actual fun Ctx.alert(msg: Any?) {
    println(msg)
}