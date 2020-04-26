package tz.co.asoft.ui.react.composites.chips

import tz.co.asoft.ui.theme.main
import tz.co.asoft.ui.react.composites.chips.Chip.Props
import tz.co.asoft.ui.react.tools.ThemedProps
import kotlinx.css.*
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.css
import styled.styledDiv

class Chip(props: Props) : RComponent<Props, RState>(props) {
    object Props : ThemedProps() {
        var label = ""
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            backgroundColor = props.theme.primaryColor.main()
            borderRadius = 1.em
            padding(0.1.em, 0.3.em)
            margin(0.em, 0.1.em)
            display = Display.inlineBlock
            color = props.theme.text.onPrimary.main()
            cursor = Cursor.pointer
        }
        +props.label
    }
}

fun RBuilder.chip(label: String, handler: RHandler<Props>) = child(Chip::class.js, Props) {
    attrs.label = label
    handler()
}