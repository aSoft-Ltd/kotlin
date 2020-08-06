package tz.co.asoft

import kotlinx.css.*
import react.RBuilder
import styled.css
import styled.styledDiv

fun RBuilder.Error(msg: String, handler: (RBuilder.(Theme) -> Unit)? = null) = ThemeConsumer { theme ->
    styledDiv {
        css { +styles.wrapper }

        styledDiv {
            css {
                color = theme.errorColor
                fontSize = 3.em
            }
            FaRegWindowClose{}
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