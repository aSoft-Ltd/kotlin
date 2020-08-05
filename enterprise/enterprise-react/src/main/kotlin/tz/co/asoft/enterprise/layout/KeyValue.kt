package tz.co.asoft.enterprise.layout

import kotlinx.css.*
import react.RBuilder
import styled.css
import styled.styledDiv

fun RBuilder.KeyValue(key: String, value: String) = styledDiv {
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