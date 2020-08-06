package tz.co.asoft

import kotlinx.css.*
import react.RBuilder
import styled.css
import styled.styledDiv
import styled.styledImg

fun RBuilder.CompanyHeader(
    logoPath: String,
    userName: String
) = ThemeConsumer { theme ->
    styledDiv {
        css {
            position = Position.relative
            width = 60.pct
            fontSize = 8.em
            textAlign = TextAlign.center
        }
        styledImg(src = logoPath) {
            css { width = 100.pct }
        }
        styledDiv {
            css {
                +theme.text.h5.clazz
            }
            +userName
        }
    }
}