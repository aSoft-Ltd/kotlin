package tz.co.asoft.ui.react.widget.radio

import tz.co.asoft.ui.theme.Theme
import tz.co.asoft.ui.theme.main
import tz.co.asoft.ui.react.widget.radio.RadioButton.Props
import kotlinext.js.js
import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.defaultValue
import react.dom.jsStyle
import styled.css
import styled.styledDiv
import styled.styledInput

class RadioButton : RComponent<Props, RState>() {
    class Props : RProps {
        var theme = Theme()
        var label = "Radio Label"
        var name = ""
        var data = mutableMapOf<String, Any>()
        var style: dynamic = js { }
        var checked: Boolean? = null
        var onChange = { _: Boolean -> }
        var css: CSSBuilder.() -> Unit = {}
        var isRequired = true
        var value: String? = null
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        attrs {
            jsStyle = props.style
        }
        css {
            display = Display.inlineFlex
            alignItems = Align.center
            +props.css
        }
        styledInput {
            css {
                position = Position.relative
                width = 1.em
                put("appearance", "none")
                height = 1.em
                borderRadius = 50.pct
                border(2.px, BorderStyle.solid, props.theme.primaryColor.main())
                focus { outline = Outline.none }
                checked {
                    after {
                        position = Position.absolute
                        left = 0.1.em
                        top = 0.1.em
                        width = 0.8.em - 4.px
                        height = 0.8.em - 4.px
                        borderRadius = 50.pct
                        backgroundColor = props.theme.primaryColor.main()
                    }
                }
            }
            attrs {
                props.value?.let { defaultValue = it }
                props.checked?.let { defaultChecked = it }
                type = InputType.radio
                name = props.name
                required = props.isRequired
                onChangeFunction = { props.onChange((it.target.unsafeCast<HTMLInputElement>()).checked) }
            }

            props.data.forEach { (k, v) ->
                attrs["data-$k"] = v
            }
        }

        styledDiv {
            css { marginLeft = 0.5.em }
            +props.label
        }
    }
}

fun RBuilder.radioButton(label: String = "", handler: RHandler<Props> = {}) = child(RadioButton::class.js, Props()) {
    attrs.label = label
    attrs.data["value"] = label
    handler()
}