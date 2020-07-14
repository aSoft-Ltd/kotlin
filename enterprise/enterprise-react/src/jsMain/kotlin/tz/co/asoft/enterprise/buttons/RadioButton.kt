package tz.co.asoft.enterprise.buttons

import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.RBuilder
import react.dom.defaultValue
import styled.css
import styled.styledDiv
import styled.styledInput
import tz.co.asoft.theme.ThemeConsumer
import tz.co.asoft.theme.primaryColor

fun RBuilder.RadioButton(
    label: String,
    name: String,
    value: String? = null,
    data: Map<String,Any>? = null,
    checked: Boolean = false,
    required: Boolean = true,
    onChange: ((Boolean) -> Unit)? = null
) = styledDiv {
    css {
        display = Display.inlineFlex
        alignItems = Align.center
    }

    ThemeConsumer { theme ->
        styledInput {
            css {
                position = Position.relative
                width = 1.em
                put("appearance", "none")
                height = 1.em
                borderRadius = 50.pct
                border(2.px, BorderStyle.solid, theme.primaryColor)
                focus { outline = Outline.none }
                checked {
                    after {
                        position = Position.absolute
                        left = 0.1.em
                        top = 0.1.em
                        width = 0.8.em - 4.px
                        height = 0.8.em - 4.px
                        borderRadius = 50.pct
                        backgroundColor = theme.primaryColor
                    }
                }
            }
            attrs {
                value?.let { defaultValue = it }
                defaultChecked = checked
                type = InputType.radio
                onChange?.let {
                    onChangeFunction = { onChange((it.target.unsafeCast<HTMLInputElement>()).checked) }
                }
            }
            attrs.name = name
            attrs.required = required

            data?.forEach { (k, v) ->
                attrs["data-$k"] = v
            }
        }

    }
    styledDiv {
        css { marginLeft = 0.5.em }
        +label
    }
}