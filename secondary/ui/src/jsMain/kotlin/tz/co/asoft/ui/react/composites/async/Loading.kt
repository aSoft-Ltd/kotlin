package tz.co.asoft.ui.react.composites.async

import kotlinx.css.*
import react.RBuilder
import react.RHandler
import styled.css
import styled.styledDiv
import tz.co.asoft.components.CState
import tz.co.asoft.components.Component
import tz.co.asoft.components.ModuleProps
import tz.co.asoft.ui.react.icons.loadingSvg
import tz.co.asoft.ui.theme.main
import tz.co.asoft.ui.react.composites.async.Loading.Props

class Loading(p: Props) : Component<Props, CState>(p) {

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