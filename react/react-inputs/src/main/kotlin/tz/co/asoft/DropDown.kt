package tz.co.asoft

import kotlinx.css.*
import kotlinx.css.Color
import kotlinx.css.properties.border
import kotlinx.css.properties.boxShadow
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.css
import styled.styledDiv
import styled.styledOption
import styled.styledSelect
import tz.co.asoft.*
import tz.co.asoft.DropDown.Props

private class DropDown(p: Props) : RComponent<Props, RState>(p) {
    class Props(
        val value: String?,
        val options: List<String>,
        val isRequired: Boolean,
        val name: String,
        val data: Map<String, Any>?,
        val multipleSelect: Boolean,
        val onChange: ((String) -> Unit)?
    ) : RProps

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

                attrs["defaultValue"] = props.value ?: props.options.first()
                props.data?.forEach { (k, v) -> attrs["data-$k"] = v }
                attrs {
                    required = props.isRequired
                    onOptionChanged {
                        it.persist()
                        props.onChange?.let {
                            it(value)
                        }
                    }
                    multiple = props.multipleSelect
                    name = props.name
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
    data: Map<String, Any>? = null,
    multipleSelect: Boolean = false,
    onChange: ((String) -> Unit)? = null
) = child(
    DropDown::class.js,
    Props(value, options, isRequired, name, data, multipleSelect, onChange)
) {}