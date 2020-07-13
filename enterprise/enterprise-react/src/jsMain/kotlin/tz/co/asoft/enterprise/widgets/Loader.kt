package tz.co.asoft.enterprise.widgets

import kotlinx.css.*
import react.RBuilder
import styled.css
import styled.styledDiv
import tz.co.asoft.theme.ThemeConsumer
import tz.co.asoft.theme.primaryColor

fun RBuilder.Loader(text: String) = ThemeConsumer { theme ->
    styledDiv {
        css { +styles.wrapper }

        styledDiv {
            css { color = theme.primaryColor }
            LoadingSvg()
        }

        styledDiv {
            css { margin(1.em) }
            +text
        }
    }
}