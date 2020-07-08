package tz.co.asoft.enterprise.simplebar

import kotlinext.js.require
import react.RBuilder
import react.RHandler

private var simpleBarCssLoaded = false

fun RBuilder.SimpleBar(handler: RHandler<SimpleBarProps>) = SimpleBarReact {
    if (!simpleBarCssLoaded) {
        require("simplebar/dist/simplebar.min.css")
        simpleBarCssLoaded = true
    }
    handler()
}