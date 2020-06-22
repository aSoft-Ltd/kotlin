package tz.co.asoft.ui.react.composites.title

import kotlinx.css.*
import kotlinx.html.DIV
import react.RBuilder
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv
import styled.styledH2
import tz.co.asoft.ui.react.icons.reacticons.faArrowLeft

fun RBuilder.titleWithBackArrow(title: String, builder: StyledDOMBuilder<DIV>.() -> Unit = {}) = styledDiv {
    css {
        display = Display.flex
        alignItems = Align.center
        cursor = Cursor.pointer
    }
    faArrowLeft {}
    styledH2 {
        css { marginLeft = 0.5.em }
        +title
    }
    builder()
}