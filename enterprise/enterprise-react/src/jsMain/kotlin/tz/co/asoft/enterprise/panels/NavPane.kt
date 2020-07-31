package tz.co.asoft.enterprise.panels

import kotlinx.css.*
import kotlinx.css.Color
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.router.dom.navLink
import styled.css
import styled.styledDiv
import styled.styledSection
import tz.co.asoft.*
import tz.co.asoft.enterprise.panels.NavPane.Props

internal class NavPane : RComponent<Props, RState>() {
    class Props(
        val menus: List<NavMenu>,
        val footer: String
    ) : RProps

    private fun RBuilder.menu(theme: Theme, m: NavMenu) = styledDiv {
        css {
            display = Display.flex
            alignItems = Align.center
            height = 3.em
            justifyContent = JustifyContent.start
            fontSize = 0.9.em
            width = 100.pct
            cursor = Cursor.pointer
            textDecoration = TextDecoration.none
            borderTop = "solid 1px ${theme.color.onPrimary}"
            transition(duration = 0.2.s)

            a {
                textDecoration = TextDecoration.none
                color = Color.inherit
                width = 100.pct
                height = 3.em
                display = Display.flex
                alignItems = Align.center
                justifyContent = JustifyContent.spaceBetween
            }
        }

        navLink<RProps>(to = m.link) {
            styledDiv {
                css {
                    textDecoration = TextDecoration.none
                    width = 100.pct
                    height = 100.pct
                    display = Display.flex
                    alignItems = Align.center
                    padding(horizontal = 1.em)
                    textDecoration = TextDecoration.none
                    justifyContent = JustifyContent.spaceBetween
                }

                styledDiv {
                    css {
                        textDecoration = TextDecoration.none
                        i {
                            marginRight = 1.em
                        }
                    }
                    +m.name
                }
            }
        }
    }

    private fun RBuilder.midSection(theme: Theme, menus: List<NavMenu>) = styledSection {
        css {
            height = 100.pct - 9.em - 1.px
            overflowY = Overflow.auto
            overflowX = Overflow.hidden
            div {
                child(".active") {
                    backgroundColor = theme.primaryColor
                    color = theme.onPrimaryColor
                }
            }

            children {
                hover {
                    backgroundColor = theme.primaryColor
                    color = theme.onPrimaryColor
                }
            }
        }
        menus.forEach {
            menu(theme, it)
        }
    }

    private fun RBuilder.footerSection(theme: Theme) = styledSection {
        css {
            minHeight = 3.em
            display = Display.flex
            alignItems = Align.center
            justifyContent = JustifyContent.center
            borderTop = "solid 1px ${theme.color.onPrimary}"
        }

        styledDiv {
            css {
                fontSize = 0.8.em
                textAlign = TextAlign.center
                padding(horizontal = 2.em)
            }
            +props.footer
        }
    }

    override fun RBuilder.render(): dynamic = ThemeConsumer { theme ->
        styledSection {
            css {
                width = 100.pct
                height = 100.vh
                display = Display.flex
                position = Position.relative
                flexDirection = FlexDirection.column
                justifyContent = JustifyContent.stretch
                backgroundColor = theme.backgroundVariantColor
                color = theme.onBackgroundVariantColor
                transition(duration = 3.s)
                zIndex = 999999
            }
            props.children()
            midSection(theme, props.menus)
            footerSection(theme)
        }
    }
}