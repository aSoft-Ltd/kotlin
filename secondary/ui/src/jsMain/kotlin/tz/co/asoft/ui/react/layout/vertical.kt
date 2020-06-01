package tz.co.asoft.ui.react.layout

import tz.co.asoft.ui.react.layout.VerticalLayout.Props
import tz.co.asoft.ui.react.tools.IStyled
import kotlinext.js.jsObject
import kotlinx.css.*
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.css
import styled.styledDiv

class VerticalLayout(p: Props) : RComponent<Props, RState>(p) {
    interface Props : IStyled {
        var isCentered: Boolean
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            display = Display.flex
            flexDirection = FlexDirection.column
            width = 100.pct
            if (props.isCentered) {
                alignItems = Align.center
            } else
                children {
                    width = 100.pct
                }
            +props.css
        }
        props.children()
    }
}

fun RBuilder.verticalLayout(handler: RHandler<Props>) = child(VerticalLayout::class.js, jsObject<Props> {}) {
    attrs {
        css = {}
        isCentered = false
    }
    handler()
}

fun RBuilder.verticalCenteredLayout(handler: RHandler<Props>) = child(VerticalLayout::class.js, jsObject<Props> { }) {
    attrs {
        css = {}
        attrs.isCentered = true
    }
    handler()
}