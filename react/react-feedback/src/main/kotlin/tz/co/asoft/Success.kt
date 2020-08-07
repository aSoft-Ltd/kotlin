package tz.co.asoft

import kotlinx.css.*
import react.RBuilder
import styled.css
import styled.styledDiv

fun RBuilder.Success(msg: String, handler: (RBuilder.(Theme) -> Unit)? = null) = ThemeConsumer { theme ->
    styledDiv {
        css { +styles.wrapper }

        styledDiv {
            css {
                color = theme.primaryColor
                fontSize = 3.em
            }
            FaCheck{}
        }

        styledDiv {
            css {
                margin(1.em)
                color = theme.onBackgroundColor
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