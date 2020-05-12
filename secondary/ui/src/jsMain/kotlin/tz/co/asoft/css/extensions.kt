package tz.co.asoft.css

import kotlinx.css.*
import tz.co.asoft.persist.tools.Cause

var CSSBuilder.justifySelf: JustifyContent
    set(value) {
        put("justify-self", value.toString())
    }
    get() {
        throw Cause("Justify self is write only property")
    }