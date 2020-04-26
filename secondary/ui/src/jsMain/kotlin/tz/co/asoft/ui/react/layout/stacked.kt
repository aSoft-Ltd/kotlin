package tz.co.asoft.ui.react.layout

import kotlinx.css.*
import tz.co.asoft.ui.react.layout.StackedLayout.Props
import tz.co.asoft.ui.react.tools.IStyled
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.css
import styled.styledDiv

class StackedLayout(p: Props) : RComponent<Props, RState>(p) {
    object Props : IStyled {
        override var css: CSSBuilder.() -> Unit = {}
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            position = Position.relative
            width = 100.pct
            height = 100.pct
            children {
                position = Position.absolute
            }
            +props.css
        }
        props.children()
    }
}

fun RBuilder.stackedLayout(handler: RHandler<Props>) = child(StackedLayout::class.js, Props, handler)