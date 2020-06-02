package tz.co.asoft.tools

import tz.co.asoft.platform.core.Ctx

actual fun Ctx.alert(msg: Any?) {
    println(msg)
}