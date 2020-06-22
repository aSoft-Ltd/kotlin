package tz.co.asoft.ui.react.widget.text.textinput

import tz.co.asoft.ui.react.tools.ThemedProps
import tz.co.asoft.ui.react.widget.text.textinput.TextMultiple.Props
import tz.co.asoft.ui.react.widget.text.textinput.TextMultiple.State
import kotlinx.html.InputType
import react.*
import react.dom.br
import styled.css
import styled.styledDiv

class TextMultiple(p: Props) : RComponent<Props, State>(p) {

    object Props : ThemedProps() {
        var hint = ""
        var type = InputType.text
        var name = ""
        var onChange = { _: String -> }
        var onBlur = {}
        var onValuesChanged = { _: List<String> -> }
    }

    class State : RState {
        var values = mutableListOf<String>()
    }

    init {
        state = State()
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                +TextInputStyles.root
                +props.css
            }

            for (i in 0..state.values.size) {
                textInput {
                    attrs {
                        type = props.type
                        name = if (i == 0) {
                            props.name
                        } else {
                            "another ${props.name}"
                        }

                        onChange = {
                            setState {
                                if (i == values.size) {
                                    values.add(it)
                                } else {
                                    if (it.isEmpty()) {
                                        values.removeAt(i)
                                    } else {
                                        values[i] = it
                                    }
                                }
                                props.onValuesChanged(values)
                            }
                        }
                    }
                }
                br {}
                br {}
                br {}
            }
        }
    }
}

fun RBuilder.textMultiple(handler: RHandler<Props> = {}) = child(TextMultiple::class.js, Props) {
    handler()
}