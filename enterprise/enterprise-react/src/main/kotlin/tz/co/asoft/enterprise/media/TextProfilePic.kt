package tz.co.asoft.enterprise.media

import kotlinx.css.*
import react.RBuilder
import styled.css
import styled.styledDiv
import tz.co.asoft.justifySelf

/**
 * Make sure to use this inside [RBuilder.AspectRationDiv]
 */
fun RBuilder.TextProfilePic(text: String, textFontSize: LinearDimension = 1.rem, radius: LinearDimension = 10.px) = styledDiv {
    css {
        display = Display.grid
        width = 100.pct
        height = 100.pct
        borderRadius = radius
        cursor = Cursor.pointer
        color = Color.white
        backgroundImage = Image("linear-gradient(to right bottom, rgb(255, 202, 0), rgb(255, 117, 0))")
        div {
            justifySelf = JustifyContent.center
            alignSelf = Align.center
            fontSize = textFontSize
        }
    }
    val tag = text.split(" ").mapNotNull { it.firstOrNull()?.toUpperCase() }
    styledDiv {
        +if (tag.size == 1) "${tag.first()}" else "${tag.first()}${tag.last()}"
    }
}