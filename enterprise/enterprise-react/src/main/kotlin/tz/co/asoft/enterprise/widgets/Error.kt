package tz.co.asoft.enterprise.widgets

import kotlinx.css.*
import react.RBuilder
import styled.css
import styled.styledDiv
import tz.co.asoft.react.icons.MdErrorOutline
import tz.co.asoft.*

fun RBuilder.Error(msg: String, handler: (RBuilder.(Theme) -> Unit)? = null) = ThemeConsumer { theme ->
    styledDiv {
        css { +styles.wrapper }

        styledDiv {
            css {
                color = theme.errorColor
                fontSize = 3.em
            }
            MdErrorOutline {}
        }

        styledDiv {
            css {
                margin(1.em)
                color = theme.errorColor
            }
            +msg
        }

        styledDiv {
            css {
                width = 100.pct
                textAlign = TextAlign.center
            }
            handler?.let { it(theme) }
        }
    }
}