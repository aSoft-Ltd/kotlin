package tz.co.asoft.ui.react.layout

import tz.co.asoft.ui.react.layout.HorizontalLayout.Props
import tz.co.asoft.ui.react.tools.IStyled
import kotlinext.js.jsObject
import kotlinx.css.Display
import kotlinx.css.display
import kotlinx.css.pct
import kotlinx.css.width
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.css
import styled.styledDiv

class HorizontalLayout(p: Props) : RComponent<Props, RState>(p) {
    interface Props : IStyled

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            display = Display.flex
            width = 100.pct
            +props.css
        }
        props.children()
    }
}

fun RBuilder.horizontalLayout(handler: RHandler<Props>) = child(HorizontalLayout::class.js, jsObject<Props> { }) {
    attrs { css = {} }
    handler()
}