package tz.co.asoft.platform.core

import kotlin.browser.window

@Deprecated("There is no Context In Js World")
actual abstract class Ctx

actual fun Ctx.alert(msg: Any?) {
    window.alert(msg.toString())
}