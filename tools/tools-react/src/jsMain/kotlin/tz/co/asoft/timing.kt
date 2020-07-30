package tz.co.asoft

import kotlin.browser.window

fun afterTimeout(timeout: Int, callback: () -> Unit) = window.setTimeout(callback, timeout)