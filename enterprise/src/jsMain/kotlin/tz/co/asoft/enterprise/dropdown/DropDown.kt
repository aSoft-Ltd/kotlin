package tz.co.asoft.enterprise.dropdown

import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.css.properties.boxShadow
import kotlinx.html.SELECT
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLSelectElement
import react.*
import styled.css
import styled.styledDiv
import styled.styledOption
import styled.styledSelect
import tz.co.asoft.enterprise.dropdown.DropDown.Props
import tz.co.asoft.enterprise.dropdown.DropDown.State
import tz.co.asoft.theme.*
import tz.co.asoft.tools.*

private class DropDown(p: Props) : RComponent<Props, State>(p) {
    class Props(
        val value: String?,
        val options: List<String>,
        val isRequired: Boolean,
        val name: String,
        val onChange: ((String) -> Unit)?
    ) : RProps

    class State(var value: String) : RState

    init {
        state = State(if (p.value?.isNotBlank() == true) p.value else p.options.first())
    }

    override fun RBuilder.render(): dynamic = ThemeConsumer { theme ->
        styledDiv {
            css {
                position = Position.relative
                padding(0.2.em)
                border(2.px, BorderStyle.solid, theme.primaryColor)
                borderRadius = 0.2.em

                hover {
                    boxShadow(theme.primaryColor, blurRadius = 10.px, spreadRadius = 2.px)
                }

                active {
                    boxShadow(theme.primaryColor, blurRadius = 10.px, spreadRadius = 2.px)
                }
                display = Display.flex
            }

            styledSelect {
                css {
                    border = "none"
                    flexBasis = FlexBasis("100%")
                    minWidth = 5.em
                    backgroundColor = Color.transparent
                    width = 100.pct
                    focus {
                        outline = Outline.none
                    }
                    +theme.dropdown_clazz
                }

                attrs {
                    required = props.isRequired
                    onOptionChanged {
                        it.persist()
                        setState { value = this@onOptionChanged.value }
                        props.onChange?.let {
                            it(state.value)
                        }
                    }
                    name = props.name
                    attrs["defaultValue"] = state.value
                    attrs["data-name"] = props.name

                    props.options.forEachIndexed { i, it ->
                        styledOption {
                            attrs {
                                key = it
                                value = if (props.isRequired) {
                                    if (i == 0) "" else it
                                } else it
                            }
                            +it
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.DropDown(
    value: String? = null,
    options: List<String>,
    isRequired: Boolean = true,
    name: String,
    onChange: ((String) -> Unit)? = null
) = child(DropDown::class.js, Props(value, options, isRequired, name, onChange)) {}