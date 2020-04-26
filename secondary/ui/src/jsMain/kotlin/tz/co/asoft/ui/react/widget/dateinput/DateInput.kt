package tz.co.asoft.ui.react.widget.dateinput

import kotlinx.css.*
import tz.co.asoft.ui.react.tools.ThemedProps
import tz.co.asoft.ui.react.tools.View
import tz.co.asoft.ui.react.widget.dateinput.DateInput.Props
import tz.co.asoft.ui.react.widget.dateinput.DateInput.State
import tz.co.asoft.ui.react.widget.text.textinput.TextInputStyles
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
import kotlin.js.Date

class DateInput(p: Props) : RComponent<Props, State>(p) {

    class Props : ThemedProps() {
        var hint = ""
        var type = InputType.date
        var label = ""
        var name = ""
        var value: Date? = null
        var onChange = { _: Date -> }
        var onBlur = {}
        var isRequired = true
    }

    class State : RState {
        var textValue = ""
        var isFocused = false
    }

    init {
        state = State()
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
                if (state.isFocused) {
                    placeholder = props.hint
                }
                name = props.name
                required = props.isRequired

                props.value?.let { defaultValue = it.toISOString().substring(0, 10) }
                type = if (state.textValue.isEmpty() && !state.isFocused) InputType.text else props.type

                onChangeFunction = {
                    state.textValue = it.target.unsafeCast<HTMLInputElement>().value
                    props.onChange(Date(Date.parse(state.textValue)))
                }

                onFocusFunction = {
                    setState { isFocused = true }
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

fun RBuilder.dateInput(name: String = "", handler: RHandler<Props> = {}) = child(DateInput::class.js, Props()) {
    attrs.data["value"] = name
    handler()
}