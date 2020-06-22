package tz.co.asoft.ui.react.composites.wrapper

import kotlinx.css.pct
import kotlinx.css.width
import react.RBuilder
import styled.css
import styled.styledDiv

fun RBuilder.wrapper(builder: RBuilder.() -> Unit = {}): dynamic = styledDiv {
    css { width = 100.pct }
    builder()
}