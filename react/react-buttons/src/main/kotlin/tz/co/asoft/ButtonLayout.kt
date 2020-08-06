package tz.co.asoft

import react.RBuilder
import react.RClass
import react.dom.span
import styled.css
import styled.styledDiv

internal fun RBuilder.ButtonLayout(name: String, icon: RClass<IconProps>?) = styledDiv {
    css {
        if (icon == null) {
            +styles.button_layout_no_icon
        } else {
            +styles.button_layout_with_icon
        }
    }
    icon?.let { it {} }
    span { +name }
}