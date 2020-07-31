package tz.co.asoft

import org.khronos.webgl.Int8Array
import org.khronos.webgl.Uint8Array
import org.w3c.dom.WindowOrWorkerGlobalScope
import kotlin.browser.window

private val isNodeJs by lazy { js("(typeof process === 'object' && typeof require === 'function')").unsafeCast<Boolean>() }
private external fun require(name: String): dynamic
private val global: dynamic = js("(typeof global !== 'undefined') ? global : self")

actual fun fillRandomBytes(array: ByteArray) {
    if (isNodeJs) {
        require("crypto").randomFillSync(Uint8Array(array.unsafeCast<Int8Array>().buffer))
    } else {
        global.crypto.getRandomValues(array)
    }
}
