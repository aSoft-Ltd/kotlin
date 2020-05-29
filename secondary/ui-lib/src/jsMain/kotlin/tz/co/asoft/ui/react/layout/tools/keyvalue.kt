package tz.co.asoft.ui.react.layout.tools

import kotlinx.css.*
import react.RBuilder
import styled.css
import styled.styledDiv

fun RBuilder.keyValue(key: String, value: String) = styledDiv {
    css {
        display = Display.grid
        gridTemplateColumns = GridTemplateColumns("1fr 1fr")
    }
    styledDiv {
        css {
            marginLeft = 1.em
            fontWeight = FontWeight.bold
        }
        +"$key:"
    }
    styledDiv {
        +value
    }
}