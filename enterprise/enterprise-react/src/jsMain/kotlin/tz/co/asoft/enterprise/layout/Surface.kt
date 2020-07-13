package tz.co.asoft.enterprise.layout

import kotlinx.css.*
import kotlinx.html.DIV
import react.RBuilder
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv
import tz.co.asoft.theme.Theme
import tz.co.asoft.theme.ThemeConsumer
import tz.co.asoft.theme.onSurfaceColor
import tz.co.asoft.theme.surfaceColor

fun RBuilder.Surface(
    bgColor: Color? = null,
    color: Color? = null,
    builder: StyledDOMBuilder<DIV>.(Theme) -> Unit
) = ThemeConsumer { theme ->
    styledDiv {
        css {
            width = 100.pct - 1.em
            margin(0.5.em)
            padding(0.5.em)
            children { maxWidth = 100.pct }
            backgroundColor = bgColor ?: theme.surfaceColor
            this.color = color ?: theme.onSurfaceColor
        }
        builder(theme)
    }
}