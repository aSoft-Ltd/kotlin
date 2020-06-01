package tz.co.asoft.ui.react.composites.async

import kotlinx.css.*
import react.RBuilder
import react.RState
import styled.css
import styled.styledDiv
import tz.co.asoft.component.Component
import tz.co.asoft.components.CState
import tz.co.asoft.components.ModuleProps
import tz.co.asoft.ui.react.composites.async.Loading.Props
import tz.co.asoft.ui.react.icons.loadingSvg
import tz.co.asoft.ui.theme.main

class Loading(p: Props) : Component<Props, RState>(p) {

    class Props : ModuleProps() {
        var msg = "Loading. . ."
        var css: CSSBuilder.() -> Unit = {}
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            height = 80.vh
            width = 100.pct
            display = Display.flex
            justifyContent = JustifyContent.center
            alignItems = Align.center
            flexDirection = FlexDirection.column
            +props.css
        }

        styledDiv {
            css {
                color = props.theme.primaryColor.main()
            }
            loadingSvg {}
        }

        styledDiv {
            css {
                margin(1.em)
            }
            +props.msg
        }
    }
}