package tz.co.asoft

import kotlinx.css.*
import react.RBuilder
import styled.css
import styled.styledDiv

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