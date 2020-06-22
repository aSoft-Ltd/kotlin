package tz.co.asoft.components

import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RHandler
import react.RState
import styled.css
import styled.styledDiv
import tz.co.asoft.component.Component
import tz.co.asoft.ui.react.icons.reacticons.faArrowLeft
import tz.co.asoft.ui.theme.main
import tz.co.asoft.components.Backable.Props

class Backable : Component<Props, RState>() {
    class Props : ModuleProps() {
        var onBack = {}
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            width = 100.pct
            display = Display.grid
            gridColumn = GridColumn("1fr")
            gap = Gap("1em")
        }
        styledDiv {
            css {
                width = 100.pct
                display = Display.flex
                justifyContent = JustifyContent.start
                cursor = Cursor.pointer
            }
            attrs.onClickFunction = { props.onBack() }
            styledDiv {
                css {
                    marginRight = 1.em
                    color = props.theme.primaryColor.main()
                }
                faArrowLeft {}
            }
            styledDiv { +"Back" }
        }
        styledDiv {
            css { width = 100.pct }
            props.children()
        }
    }
}

fun RBuilder.backable(handler: RHandler<Props>) = child(Backable::class.js, Props(), handler)