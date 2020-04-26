package tz.co.asoft.tools

import tz.co.asoft.platform.core.Ctx
import kotlin.browser.window

actual fun Ctx.alert(msg: Any?) {
    window.alert(msg.toString())
}