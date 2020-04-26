package tz.co.asoft.ui.react.dialog

import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RHandler
import styled.css
import styled.styledDiv
import tz.co.asoft.components.CState
import tz.co.asoft.components.Component
import tz.co.asoft.components.ModuleProps
import tz.co.asoft.ui.react.tools.onDesktop
import tz.co.asoft.ui.react.tools.onMobile

class Dialog : Component<Dialog.Props, CState>() {
    class Props : ModuleProps() {
        var desktopWidth = 50.pct
        var mobileWidth = 90.pct
        var width: LinearDimension = 50.pct
        var onExit = {}
    }

    private fun RBuilder.card() = styledDiv {
        css {
            position = Position.relative
            backgroundColor = Color.white
            borderRadius = 8.px
            minHeight = 10.em
            paddingTop = 1.em
            onDesktop {
                width = props.desktopWidth
            }
            onMobile {
                width = props.mobileWidth
            }
        }
        styledDiv {
            css {
                position = Position.absolute
                top = 0.px
                right = 0.px
                padding(0.5.em)
                cursor = Cursor.pointer
                fontSize = 1.5.em
                userSelect = UserSelect.none
            }
            +"X"
            attrs.onClickFunction = { props.onExit() }
        }
        props.children()
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            width = 100.vw
            height = 100.vh
            position = Position.fixed
            left = 0.px
            top = 0.px
            backgroundColor = Color("#00000020")
            display = Display.flex
            justifyContent = JustifyContent.center
            alignItems = Align.center
            zIndex = 99999
        }
        card()
    }
}

fun RBuilder.dialog(handler: RHandler<Dialog.Props> = {}) = child(Dialog::class.js, Dialog.Props(), handler)