package tz.co.asoft.enterprise.checkbox

import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.defaultValue
import styled.css
import styled.styledDiv
import styled.styledInput
import tz.co.asoft.enterprise.checkbox.CheckBox.Props
import tz.co.asoft.enterprise.checkbox.CheckBox.State
import tz.co.asoft.theme.ThemeConsumer
import tz.co.asoft.theme.primaryColor

private class CheckBox(p: Props) : RComponent<Props, State>(p) {
    class Props(
        val name: String,
        val label: String,
        val value: String? = null,
        val checked: Boolean? = null,
        val isRequired: Boolean = true,
        val size: LinearDimension,
        val borderSize: LinearDimension,
        val lineSize: LinearDimension, // in px
        val data: Map<String, Any>? = null,
        val onChanged: ((Boolean) -> Unit)?
    ) : RProps

    class State(var checked: Boolean) : RState

    init {
        state = State(p.checked ?: false)
    }

    private fun RBuilder.realInput() = styledInput {
        css { display = Display.none }
        attrs {
            name = props.name
            checked = state.checked
            props.value?.let { defaultValue = it }
            required = props.isRequired
        }
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        val borderSize = props.borderSize //in px
        val size = props.size // in em
        val lineSize = props.lineSize.value.toIntOrNull() ?: 1 // in px

        css {
            +styles.root
        }

        realInput()
        ThemeConsumer { theme ->
            styledDiv {
                attrs.onClickFunction = {
                    setState {
                        checked = !checked
                        props.onChanged?.invoke(checked)
                    }
                }
                props.data?.forEach { (k, v) ->
                    attrs["data-$k"] = v
                }
                css {
                    +styles.root
                    width = size
                    height = size
                    border = "solid ${borderSize.value} ${theme.primaryColor}"
                }

                styledDiv {
                    css {
                        borderRight = "solid ${lineSize}px ${theme.primaryColor}"
                        if (state.checked) {
                            +styles.tailLeftChecked
                        } else {
                            left = (-lineSize / 2).px
                            +styles.tailLeftUnChecked
                        }
                    }
                }

                styledDiv {
                    css {
                        borderLeft = "solid ${lineSize}px ${theme.primaryColor}"
                        if (state.checked) {
                            +styles.tailRightChecked
                        } else {
                            right = (-lineSize / 2).px
                            +styles.tailRightUnChecked
                        }
                    }
                }
            }

            styledDiv {
                css {
                    +styles.root
                    paddingLeft = 0.3.em
                }
                +props.label
            }
        }
    }
}

fun RBuilder.CheckBox(
    name: String,
    label: String,
    value: String? = null,
    checked: Boolean? = null,
    isRequired: Boolean = true,
    size: LinearDimension = 1.em,
    borderSize: LinearDimension = 2.px,
    lineSize: LinearDimension = 1.px,
    data: Map<String, Any>? = null,
    onChanged: ((Boolean) -> Unit)? = null
) = child(CheckBox::class.js, Props(name, label, value, checked, isRequired, size, borderSize, lineSize, data, onChanged)) {}