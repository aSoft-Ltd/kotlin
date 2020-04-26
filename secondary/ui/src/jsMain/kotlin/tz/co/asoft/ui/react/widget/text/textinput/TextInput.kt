package tz.co.asoft.ui.react.widget.text.textinput

import kotlinx.css.*
import tz.co.asoft.ui.tools.newJsObject
import tz.co.asoft.ui.react.tools.ThemedProps
import tz.co.asoft.ui.react.tools.View
import tz.co.asoft.ui.react.widget.text.textinput.TextInput.Props
import tz.co.asoft.ui.react.widget.text.textinput.TextInput.State
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onBlurFunction
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onFocusFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.defaultValue
import styled.css
import styled.styledDiv
import styled.styledInput
import kotlin.browser.document

class TextInput(props: Props) : RComponent<Props, State>(props) {

    class Props : ThemedProps() {
        var hint = ""
        var type = InputType.text
        var label = ""
        var name = ""
        var value = ""
        var onChange = { _: String -> }
        var onBlur = {}
        var isRequired = true
    }

    class State(props: Props) : RState {
        var textValue = ""
        var isFocused = props.value.isNotBlank()
    }

    init {
        state = State(props)
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            +TextInputStyles.root
            +props.css
        }

        styledDiv {
            css {
                if (state.isFocused) {
                    +TextInputStyles.tagNameFocused
                } else {
                    +TextInputStyles.tagNameUnFocused
                }
            }
            +props.label
        }


        styledInput {
            attrs {
                id = View.getId()
                name = props.name
                defaultValue = props.value
                type = if (props.type == InputType.tel) {
                    InputType.number
                } else {
                    props.type
                }

                if (state.isFocused) {
                    placeholder = props.hint
                }

                required = props.isRequired

                onChangeFunction = {
                    state.textValue = (document.getElementById(id) as HTMLInputElement).value
                    props.onChange(state.textValue)
                }

                onFocusFunction = {
                    setState {
                        isFocused = true
                    }
                }

                onBlurFunction = {
                    setState {
                        isFocused = textValue.isNotEmpty()
                    }
                    props.onBlur()
                }
            }

            props.data.forEach { (k, v) ->
                attrs["data-$k"] = v
            }

            css {
                outline = Outline.none
                +TextInputStyles.input
                color = Color(props.theme.text.onBackground.light)
                borderBottomColor = Color(props.theme.primaryColor.main)
            }
        }
    }
}

fun RBuilder.textInput(name: String = "",handler: RHandler<Props> = {}) = child(TextInput::class.js, Props()) {
    attrs.type = InputType.text
    attrs.data["value"] = name
    handler()
}