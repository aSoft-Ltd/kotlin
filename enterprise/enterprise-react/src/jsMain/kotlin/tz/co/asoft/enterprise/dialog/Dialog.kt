package tz.co.asoft.enterprise.dialog

import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.*
import styled.css
import styled.styledDiv
import tz.co.asoft.react.icons.MdClose
import tz.co.asoft.theme.ThemeConsumer
import tz.co.asoft.theme.backgroundColor
import tz.co.asoft.theme.onSurfaceColor
import tz.co.asoft.theme.surfaceColor
import tz.co.asoft.tools.onDesktop
import tz.co.asoft.tools.onMobile

class Dialog : RComponent<Dialog.Props, RState>() {
    class Props(
        val desktopWidth: LinearDimension,
        val mobileWidth: LinearDimension,
        val width: LinearDimension,
        val onExit: () -> Unit
    ) : RProps

    private fun RBuilder.card() = ThemeConsumer { theme ->
        styledDiv {
            css {
                position = Position.relative
                backgroundColor = theme.surfaceColor
                color = theme.onSurfaceColor
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
                MdClose {}
                attrs.onClickFunction = { props.onExit() }
            }
            props.children()
        }
    }

    override fun RBuilder.render(): dynamic = ThemeConsumer { theme ->
        styledDiv {
            css {
                width = 100.vw
                height = 100.vh
                position = Position.fixed
                left = 0.px
                top = 0.px
                backgroundColor = theme.backgroundColor.withAlpha(0.4)
                display = Display.flex
                justifyContent = JustifyContent.center
                alignItems = Align.center
                zIndex = 99999
            }
            card()
        }
    }
}

fun RBuilder.Dialog(
    desktopWidth: LinearDimension = 50.pct,
    mobileWidth: LinearDimension = 90.pct,
    width: LinearDimension = 50.pct,
    onExit: () -> Unit,
    handler: RHandler<Dialog.Props>
) = child(Dialog::class.js, Dialog.Props(desktopWidth, mobileWidth, width, onExit), handler)