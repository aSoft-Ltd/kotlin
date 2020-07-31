package tz.co.asoft

import kotlinx.css.CSSBuilder
import kotlinx.css.JustifyContent

var CSSBuilder.gridArea: String
    set(value) = put("grid-area", value)
    get() = ""

var CSSBuilder.justifySelf: JustifyContent
    set(value) {
        put("justify-self", value.toString())
    }
    get() {
        throw Exception("Justify self is write only property")
    }