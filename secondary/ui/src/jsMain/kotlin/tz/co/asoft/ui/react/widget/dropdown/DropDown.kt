package tz.co.asoft.ui.react.widget.dropdown

import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.css.properties.boxShadow
import kotlinx.html.js.onChangeFunction
import react.*
import styled.css
import styled.styledDiv
import styled.styledOption
import styled.styledSelect
import tz.co.asoft.ui.react.tools.ThemedProps
import tz.co.asoft.ui.react.tools.value
import tz.co.asoft.ui.react.widget.dropdown.DropDownComponent.Props
import tz.co.asoft.ui.react.widget.dropdown.DropDownComponent.State
import tz.co.asoft.ui.theme.main

class DropDownComponent(p: Props) : RComponent<Props, State>(p) {

    class Props : ThemedProps() {
        var onChange = { _: Int, _: String -> }
        var value = ""
        var options = listOf<String>()
        var isRequired = true
        var name = ""
    }

    class State(p: Props) : RState {
        var value = if (p.value.isBlank()) p.options.first() else p.value
    }

    init {
        state = State(p)
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            position = Position.relative
            padding(0.2.em)
            border(2.px, BorderStyle.solid, props.theme.primaryColor.main())
            borderRadius = 0.2.em

            hover {
                boxShadow(props.theme.primaryColor.main(), blurRadius = 10.px, spreadRadius = 2.px)
            }

            active {
                boxShadow(props.theme.primaryColor.main(), blurRadius = 10.px, spreadRadius = 2.px)
            }
            +props.css
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
            }

            attrs {
                required = props.isRequired
                onChangeFunction = {
                    it.asDynamic().persist()
                    setState { value = it.target.value }
                    props.onChange(props.options.indexOf(state.value), state.value)
                }
                name = props.name
            }

            attrs["defaultValue"] = state.value

            props.data.forEach { (k, v) ->
                attrs["data-$k"] = v
            }

            props.options.forEachIndexed { i, it ->
                styledOption {
                    attrs {
                        value = if (props.isRequired) {
                            if (i == 0) {
                                ""
                            } else {
                                it
                            }
                        } else {
                            it
                        }
                    }
                    +it
                }
            }
        }
    }
}

fun RBuilder.dropDown(name: String = "", handler: RHandler<Props>) = child(DropDownComponent::class.js, Props()) {
    attrs.data["value"] = name
    attrs.name = name
    handler()
}