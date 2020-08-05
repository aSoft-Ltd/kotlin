package tz.co.asoft.enterprise.web

import kotlinx.css.*
import kotlinx.css.properties.s
import kotlinx.html.js.onClickFunction
import react.RBuilder
import styled.animation
import styled.css
import styled.styledDiv
import styled.styledLabel
import tz.co.asoft.*
import tz.co.asoft.enterprise.layout.Grid

fun RBuilder.TransparentToolbar(header: String, actions: List<Handler>) = ThemeConsumer { theme ->
    Grid {
        css {
            position = Position.absolute
            top = 0.px
            left = 0.px
            width = 100.vw
            zIndex = 10
            onMobile {
                height = 8.vh
                gridTemplateColumns = GridTemplateColumns("auto auto")
                padding(vertical = 0.px, horizontal = 10.px)
            }
            onDesktop {
                height = 10.vh
                gridTemplateColumns = GridTemplateColumns("auto auto")
                padding(vertical = 1.rem, horizontal = 10.pct)
            }
            children { alignSelf = Align.center }
        }

        styledDiv {
            css {
                onDesktop { alignSelf = Align.center }
                fontWeight = FontWeight.bolder
                fontSize = 2.5.em
                position = Position.relative
                animation(2.s) {
                    from { right = 100.px }
                    to { right = 0.px }
                }
            }

            styledLabel {
                css { cursor = Cursor.pointer }
                +header
            }
        }

        Grid(cols = "auto auto") {
            css {
                width = LinearDimension.auto
                justifySelf = JustifyContent.end
                children {
                    +theme.text.h4.clazz
                    justifySelf = JustifyContent.end
                }
                marginRight = 0.5.em
                position = Position.relative
                animation(2.s) {
                    from { left = 100.vh }
                    to { left = 0.px }
                }
            }

            for (a in actions) styledDiv {
                css {
                    cursor = Cursor.pointer
                    userSelect = UserSelect.none
                }
                +a.name
                attrs.onClickFunction = { a.callback() }
            }
        }
    }
}