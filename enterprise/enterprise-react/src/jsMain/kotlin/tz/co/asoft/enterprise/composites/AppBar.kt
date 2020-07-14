package tz.co.asoft.enterprise.composites

import kotlinx.css.*
import kotlinx.html.DIV
import react.RBuilder
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv
import tz.co.asoft.theme.ThemeConsumer
import tz.co.asoft.theme.onSurfaceColor
import tz.co.asoft.theme.surfaceColor
import tz.co.asoft.tools.justifySelf

fun RBuilder.AppBar(
    left: (StyledDOMBuilder<DIV>.() -> Unit)? = null,
    middle: (StyledDOMBuilder<DIV>.() -> Unit)? = null,
    right: (StyledDOMBuilder<DIV>.() -> Unit)? = null
) = ThemeConsumer { theme ->
    styledDiv {
        css {
            display = Display.grid
            position = Position.sticky
            top = 0.px
            gridTemplateColumns = GridTemplateColumns("auto auto auto")
            backgroundColor = theme.surfaceColor
            color = theme.onSurfaceColor
            padding(1.em)
        }

        styledDiv {
            css {
                alignSelf = Align.center
                justifySelf = JustifyContent.start
            }
            left?.invoke(this)
        }

        styledDiv {
            css {
                justifySelf = JustifyContent.center
            }
            middle?.invoke(this)
        }

        styledDiv {
            css {
                alignSelf = Align.center
                justifySelf = JustifyContent.right
            }
            right?.invoke(this)
        }
    }
}