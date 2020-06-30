package tz.co.asoft.enterprise.dialog

import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.*
import styled.css
import styled.styledDiv
import tz.co.asoft.tools.onDesktop
import tz.co.asoft.tools.onMobile

class Dialog : RComponent<Dialog.Props, RState>() {
    class Props(
        val desktopWidth: LinearDimension,
        val mobileWidth: LinearDimension,
        val width: LinearDimension,
        val onExit: () -> Unit
    ) : RProps

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

fun RBuilder.Dialog(
    desktopWidth: LinearDimension = 50.pct,
    mobileWidth: LinearDimension = 90.pct,
    width: LinearDimension = 50.pct,
    onExit: () -> Unit,
    handler: RHandler<Dialog.Props>
) = child(Dialog::class.js, Dialog.Props(desktopWidth, mobileWidth, width, onExit), handler)