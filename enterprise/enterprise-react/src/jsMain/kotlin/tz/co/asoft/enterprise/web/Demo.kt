package tz.co.asoft.enterprise.web

import kotlinx.css.*
import react.RBuilder
import styled.css
import styled.styledDiv
import tz.co.asoft.clazz
import tz.co.asoft.enterprise.layout.Grid
import tz.co.asoft.justifySelf

class Demo(
    val header: String,
    val paragraphs: List<String>
)

fun RBuilder.Demo(d: Demo) = Grid { theme ->
    css { padding(vertical = 3.em) }

    Grid {
        css {
            textAlign = TextAlign.center
            +theme.text.h2.clazz
        }
        +d.header
    }

    for (p in d.paragraphs) styledDiv {
        css { justifySelf = JustifyContent.center }
        +p
    }
}