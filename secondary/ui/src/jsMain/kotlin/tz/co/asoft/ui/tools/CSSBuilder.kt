package tz.co.asoft.ui.tools

import kotlinx.css.CSSBuilder

var CSSBuilder.gridArea: String
    set(value) = put("grid-area", value)
    get() = ""