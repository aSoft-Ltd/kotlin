package tz.co.asoft.components

import kotlinx.css.*
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RState
import styled.css
import styled.styledDiv
import tz.co.asoft.component.Component
import tz.co.asoft.components.BadgeUI.Props
import tz.co.asoft.ui.react.icons.loadingSvg
import tz.co.asoft.ui.react.icons.reacticons.faSyncAlt
import tz.co.asoft.ui.react.tools.onDesktop
import tz.co.asoft.ui.react.tools.onMobile

class Badge {
    var value = "?"
    var iconRender: RBuilder.() -> Unit = { faSyncAlt {} }
    var title = "Products"
}

class BadgeUI(p: Props) : Component<Props, RState>(p) {
    class Props : ModuleProps() {
        var badge: Badge? = null
        var css: CSSBuilder.() -> Unit = {}
        var onClick = {}
    }

    private fun RBuilder.loading() = styledDiv {
        css {
            flexBasis = FlexBasis("100.pct")
            width = 100.pct
            display = Display.flex
            justifyContent = JustifyContent.center
            alignItems = Align.center
            fontSize = 3.5.em
        }
        styledDiv { loadingSvg { } }
    }

    private fun RBuilder.content(b: Badge) {
        styledDiv {
            css {
                flexBasis = FlexBasis("70.pct")
                display = Display.flex
                flexDirection = FlexDirection.column
            }
            styledDiv {
                css {
                    fontSize = 2.2.em
                }
                +b.value
            }
            styledDiv {
                +b.title
            }
        }

        styledDiv {
            css {
                flexBasis = FlexBasis("30.pct")
                fontSize = 3.5.em
            }
            b.apply { iconRender() }
        }
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            display = Display.flex
            onDesktop {
                flexBasis = FlexBasis("20%")
            }

            onMobile {
                flexBasis = FlexBasis("48%")
            }

            minHeight = 5.em
            padding(horizontal = 1.em)
            marginBottom = 1.em
            justifyContent = JustifyContent.spaceBetween
            alignItems = Align.center
            backgroundColor = Color("#${(5263440..16777215).random().toString(16)}").withAlpha((2..7).random().toDouble() / 10)
            cursor = Cursor.pointer
            border = "solid 1px transparent"
            transition(duration = 0.5.s)
            hover {
                border = "solid 1px ${props.theme.primaryColor.main}"
            }
            +props.css
            attrs.onClickFunction = { props.onClick() }
        }
        val badge = props.badge
        if (badge != null) {
            content(badge)
        } else {
            loading()
        }
    }
}