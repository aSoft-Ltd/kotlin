package tz.co.asoft

import kotlinx.css.*
import kotlinx.css.Color
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
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
import tz.co.asoft.DateTimeInput.Props
import tz.co.asoft.DateTimeInput.State
import kotlin.js.Date
import kotlin.lazy

private class DateTimeInput(p: Props) : RComponent<Props, State>(p) {
    class Props(
        val name: String,
        val label: String,
        val hint: String,
        val type: InputType,
        val value: Date?,
        val data: Map<String, Any>?,
        val isRequired: Boolean
    ) : RProps

    class State : RState {
        var textValue = ""
        var isFocused = false
    }

    init {
        state = State()
    }

    private val INPUT_ID by lazy { UIID.getId("date") }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {+styles.root}

        styledDiv {
            css {
                if (state.isFocused) {
                    +styles.labelFocused
                } else {
                    +styles.labelUnFocused
                }
            }
            +props.label
        }


        ThemeConsumer { theme ->
            styledInput {
                attrs {
                    id = INPUT_ID.value
                    if (state.isFocused) {
                        placeholder = props.hint
                    }
                    name = props.name
                    required = props.isRequired

                    props.value?.let { defaultValue = it.toISOString().substring(0, 10) }
                    type = if (state.textValue.isEmpty() && !state.isFocused) InputType.text else props.type

                    onChangeFunction = { e ->
                        e.target?.to<HTMLInputElement>()?.let {
                            state.textValue = it.value
                        }
                    }

                    onFocusFunction = {
                        setState { isFocused = true }
                    }

                    onBlurFunction = {
                        setState {
                            isFocused = textValue.isNotEmpty()
                        }
                    }
                }

                props.data?.forEach { (k, v) ->
                    attrs["data-$k"] = v
                }

                css {
                    outline = Outline.none
                    +styles.input
                    color = theme.onBackgroundColor
                    borderBottomColor = theme.primaryColor
                }
            }
        }
    }
}

fun RBuilder.DateTimeInput(
    name: String,
    label: String = name,
    hint: String = "",
    type: InputType = InputType.date,
    value: Date? = null,
    data: Map<String, Any>? = null,
    isRequired: Boolean = true
) = child(DateTimeInput::class.js, Props(name, label, hint, type, value, data, isRequired)) {}
