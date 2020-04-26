package tz.co.asoft.ui.react.layout

import kotlinx.css.LinearDimension
import kotlinx.css.height
import kotlinx.css.width
import react.RBuilder
import styled.css
import styled.styledDiv

fun RBuilder.blank(w: LinearDimension? = null, h: LinearDimension? = null) = styledDiv {
    css {
        w?.let { width = it }
        h?.let { height = it }
    }
}