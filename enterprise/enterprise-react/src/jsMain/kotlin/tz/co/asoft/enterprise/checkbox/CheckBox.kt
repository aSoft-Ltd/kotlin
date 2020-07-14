package tz.co.asoft.enterprise.checkbox

import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.RBuilder
import react.dom.defaultValue
import styled.css
import styled.styledDiv
import styled.styledInput
import styled.styledLabel
import tz.co.asoft.theme.ThemeConsumer
import tz.co.asoft.theme.backgroundColor
import tz.co.asoft.theme.onPrimaryColor
import tz.co.asoft.theme.primaryColor
import tz.co.asoft.tools.justifySelf
import tz.co.asoft.tools.to

fun RBuilder.CheckBox(
    name: String,
    value: String,
    label: String = value,
    checked: Boolean = false,
    disabled: Boolean = false,
    required: Boolean = false,
    data: Map<String, Any>? = null,
    onChanged: ((Boolean) -> Unit)? = null
) = ThemeConsumer { theme ->
    styledLabel {
        css {
            display = Display.grid
            gridTemplateColumns = GridTemplateColumns("auto 1fr")
            gap = Gap("0.2em")
            cursor = Cursor.pointer
            children {
                alignSelf = Align.center
                justifySelf = JustifyContent.start
            }
        }
        styledInput(type = InputType.checkBox, name = name) {
            css {
                position = Position.relative
                cursor = Cursor.pointer
                width = 3.em
                height = 1.5.em
                put("appearance", "none")
                backgroundColor = theme.primaryColor.withAlpha(0.3)
                outline = Outline.none
                borderRadius = 1.5.em
                transition(duration = 0.5.s)
                boxShadowInset(
                    offsetX = 0.px,
                    offsetY = 0.px,
                    blurRadius = 2.px,
                    color = theme.backgroundColor.withAlpha(0.2)
                )
                checked {
                    backgroundColor = theme.primaryColor
                    before { left = 1.5.em }
                }
                before {
                    content = "".quoted
                    position = Position.absolute
                    width = 1.5.em
                    height = 1.5.em
                    borderRadius = 50.pct
                    top = 0.px
                    left = 0.px
                    backgroundColor = theme.primaryColor
                    boxShadow(
                        offsetX = 0.px,
                        offsetY = 0.px,
                        blurRadius = 2.px,
                        color = theme.onPrimaryColor
                    )
                    transform { scale(1.1) }
                    transition(duration = .5.s)
                }
            }

            data?.forEach { (k, v) -> attrs["data-$k"] = v }
            attrs.defaultValue = value
            attrs.defaultChecked = checked
            attrs.readonly = disabled
            if (disabled) attrs.disabled = disabled
            attrs.required = required
            attrs.onChangeFunction = { ev ->
                ev.target?.to<HTMLInputElement>()?.checked?.let { onChanged?.invoke(it) }
            }
        }
        styledDiv {
            css { cursor = Cursor.pointer }
            +label
        }
    }
}