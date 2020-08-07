package tz.co.asoft

import kotlinext.js.require
import react.RBuilder
import react.RHandler

private var simpleBarCssLoaded = false

fun RBuilder.Scroller(handler: RHandler<ScrollerProps>) = SimpleBarReact {
    if (!simpleBarCssLoaded) {
        require("simplebar/dist/simplebar.min.css")
        simpleBarCssLoaded = true
    }
    handler()
}