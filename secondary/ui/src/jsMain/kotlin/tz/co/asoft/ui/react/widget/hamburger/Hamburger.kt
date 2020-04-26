package tz.co.asoft.ui.react.widget.hamburger

import tz.co.asoft.ui.react.tools.ThemedProps
import tz.co.asoft.ui.react.tools.isMobile
import tz.co.asoft.ui.react.widget.hamburger.Hamburger.Props
import tz.co.asoft.ui.react.widget.hamburger.Hamburger.State
import kotlinx.css.*
import kotlinx.css.properties.Angle
import kotlinx.css.properties.deg
import kotlinx.css.properties.rotateZ
import kotlinx.css.properties.transform
import kotlinx.html.js.onClickFunction
import react.*
import styled.css
import styled.styledDiv
import styled.styledSpan

class Hamburger(p: Props) : RComponent<Props, State>(p) {

    class Props : ThemedProps() {
        var size = 2.em
        var onToggled = { _: Boolean -> }
        var isOpen = isMobile
    }

    class State(p: Props = Props()) : RState {
        var isOpen = p.isOpen
    }

    init {
        state = State(p)
    }

    private fun lineStyles(defaultTop: LinearDimension, finalAngle: Angle): CSSBuilder.() -> Unit = {
        +HamburgerStyles.line
        props.theme.let {
            borderTop = "solid 1px ${it.primaryColor.main}"
            borderBottom = "solid 1px ${it.primaryColor.main}"
            backgroundColor = Color(it.primaryColor.main)
        }

        if (!state.isOpen) {
            top = 50.pct
            transform {
                rotateZ(finalAngle)
            }
        } else {
            top = defaultTop
        }
    }

    override fun RBuilder.render(): dynamic = styledDiv {
//        state.isOpen = props.isOpen
        val size = props.size
        attrs.onClickFunction = {
            setState {
                isOpen = !isOpen
                props.onToggled(isOpen)
            }
        }

        props.data.forEach { (k, v) ->
            attrs["data-$k"] = v
        }

        css {
            +HamburgerStyles.wrapper
            +props.css
            width = size
            height = size
        }

        styledSpan {
            css {
                +lineStyles(30.pct, (-45).deg)
            }
        }

        styledSpan {
            css {
                +lineStyles(50.pct, 45.deg)
            }
        }

        styledSpan {
            css {
                +lineStyles(70.pct, (-45).deg)
            }
        }
    }
}

fun RBuilder.hamburger(handler: RHandler<Props> = {}) = child(Hamburger::class.js, Props()) {
    attrs.data["value"] = "hamburger"
    handler()
}