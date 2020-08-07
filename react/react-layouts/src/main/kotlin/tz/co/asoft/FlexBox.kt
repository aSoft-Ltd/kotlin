package tz.co.asoft

import kotlinx.css.Display
import kotlinx.css.FlexDirection
import kotlinx.css.display
import kotlinx.css.flexDirection
import kotlinx.html.DIV
import react.RBuilder
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv

fun RBuilder.FlexBox(
    direction: FlexDirection = FlexDirection.row,
    builder: StyledDOMBuilder<DIV>.(Theme) -> Unit
) = ThemeConsumer { theme ->
    styledDiv {
        css {
            display = Display.flex
            flexDirection = direction
        }
        builder(theme)
    }
}