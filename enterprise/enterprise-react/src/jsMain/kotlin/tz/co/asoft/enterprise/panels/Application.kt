package tz.co.asoft.enterprise.panels

import kotlinx.css.*
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.router.dom.redirect
import react.router.dom.route
import react.router.dom.switch
import styled.css
import styled.styledDiv
import styled.styledImg
import styled.styledSection
import tz.co.asoft.module.AbstractModuleRoute
import tz.co.asoft.enterprise.panels.Application.Props
import tz.co.asoft.react.icons.FaBars
import tz.co.asoft.react.icons.FaUserAlt
import tz.co.asoft.theme.*
import tz.co.asoft.tools.onDesktop
import tz.co.asoft.tools.onMobile
import tz.co.asoft.tools.onPaper

internal class Application : RComponent<Props, RState>() {
    class Props(
        val title: String,
        val photoUrl: String,
        val routes: List<AbstractModuleRoute<out RProps>>,
        val onDrawerOpen: () -> Unit
    ) : RProps

    private fun RBuilder.appBar() = ThemeConsumer { theme ->
        styledDiv {
            css {
                display = Display.flex
                alignItems = Align.center
                justifyContent = JustifyContent.spaceBetween
                onMobile {
                    height = 4.em
                }
                onDesktop {
                    height = 0.em
                    display = Display.none
                }
                onPaper {
                    height = 0.em
                    display = Display.none
                }
                width = 100.pct
                backgroundColor = theme.primaryColor
                color = theme.onPrimaryColor
                borderBottom = "solid 2px ${theme.color.onPrimary}"
                position = Position.sticky
                top = 0.px
                transition(duration = 3.s)
            }

            styledDiv {
                css {
                    display = Display.flex
                    alignItems = Align.center
                    margin(horizontal = 1.em)
                    fontSize = 1.2.em
                }

                styledDiv {
                    css {
                        onDesktop {
                            display = Display.none
                        }
                        height = 1.em
                        width = 1.5.em
                    }
                    FaBars {}
                    attrs.onClickFunction = {
                        props.onDrawerOpen()
                    }
                }

                styledDiv {
                    css {
                        position = Position.relative
                        onDesktop {
                            left = (-1.5).em
                            fontSize = 1.em
                        }
                        margin(horizontal = 1.em)
                        fontSize = 1.2.em
                    }
                    +props.title
                }
            }

            styledDiv {
                css {
                    display = Display.flex
                    flexWrap = FlexWrap.wrap
                    alignItems = Align.center
                    marginRight = 0.5.em
                }

                styledDiv {
                    css {
                        backgroundColor = theme.onPrimaryColor
                        borderRadius = 50.pct
                        +styles.userImage
                    }

                    if (props.photoUrl.isNotEmpty()) {
                        styledImg(src = props.photoUrl) {
                            css { +styles.userImage }
                        }
                    } else {
                        FaUserAlt { }
                    }
                }
            }
        }
    }

    private fun RBuilder.contentSection() = styledSection {
        css {
            width = 100.pct
            overflowX = Overflow.hidden
            overflowY = Overflow.scroll
            onMobile {
                height = 100.pct - 4.em
            }
            onDesktop {
                height = 100.pct
            }
            onPaper {
                height = LinearDimension.auto
            }
        }
        switch {
            for (r in props.routes) {
                route(r.path, exact = true, strict = true, render = r.render)
            }
            redirect(to = "/")
        }
    }

    override fun RBuilder.render(): dynamic = ThemeConsumer { theme ->
        styledSection {
            css {
                position = Position.relative
                backgroundColor = theme.backgroundColor
                height = 100.vh
            }
            appBar()
            contentSection()
        }
    }
}