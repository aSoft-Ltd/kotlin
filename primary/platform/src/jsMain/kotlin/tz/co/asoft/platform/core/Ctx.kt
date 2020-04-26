package tz.co.asoft.platform.core

import kotlin.browser.window

actual abstract class Ctx

actual fun Ctx.alert(msg: Any?) {
    window.alert(msg.toString())
}