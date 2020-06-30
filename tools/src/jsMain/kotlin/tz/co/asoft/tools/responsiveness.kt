package tz.co.asoft.tools

import kotlinx.css.CSSBuilder
import kotlin.browser.window

fun CSSBuilder.onMobile(width: Int = 480, builder: CSSBuilder.() -> Unit) {
    media("only screen and (max-width: ${width}px), only screen and (orientation: portrait)", builder)
}

fun CSSBuilder.onDesktop(width: Int = 1224, builder: CSSBuilder.() -> Unit) {
    media("only screen and (min-width : ${width}px), only screen and (orientation: landscape)", builder)
}

fun CSSBuilder.onPaper(builder: CSSBuilder.() -> Unit) = media("print", builder)

val isDesktop get() = window.screen.availWidth > window.screen.availHeight

val isMobile get() = !isDesktop