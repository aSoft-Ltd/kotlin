package tz.co.asoft.enterprise.layout

import kotlinx.css.*
import kotlinx.html.DIV
import react.RBuilder
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv
import tz.co.asoft.justifySelf
import kotlin.Float

fun RBuilder.AspectRationDiv(
    aspectRatio: Float = 1f / 1f,
    builder: StyledDOMBuilder<DIV>.() -> Unit
) = styledDiv {
    css {
        position = Position.relative
        width = 100.pct
        after {
            content = "".quoted
            display = Display.block
            paddingTop = 100.pct / aspectRatio
        }
    }
    Grid {
        css {
            position = Position.absolute
            top = 0.px
            left = 0.px
            width = 100.pct
            height = 100.pct
            children {
                justifySelf = JustifyContent.center
                alignSelf = Align.center
            }
        }
        builder()
    }
}