package tz.co.asoft.enterprise.composites

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.css.*
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import kotlinx.html.DIV
import kotlinx.html.js.onClickFunction
import react.*
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv
import tz.co.asoft.enterprise.composites.NavigationDrawer.Props
import tz.co.asoft.enterprise.composites.NavigationDrawer.State
import tz.co.asoft.enterprise.simplebar.SimpleBar
import tz.co.asoft.react.icons.MdClose
import tz.co.asoft.theme.*
import tz.co.asoft.tools.onDesktop
import tz.co.asoft.tools.onMobile
import tz.co.asoft.tools.onPaper

private class NavigationDrawer : RComponent<Props, State>(), CoroutineScope by CoroutineScope(SupervisorJob()) {
    class Props(
        val drawer: RBuilder.() -> Unit,
        val content: RBuilder.() -> Unit,
        val portraitWidth: LinearDimension,
        val landscapeWidth: LinearDimension
    ) : RProps

    class State(var drawerOpen: Boolean) : RState

    init {
        state = State(navDrawerState.value)
    }

    override fun componentDidMount() {
        launch {
            navDrawerState.collect {
                setState { drawerOpen = it }
            }
        }
    }

    override fun componentWillUnmount() {
        cancel()
    }

    private fun RBuilder.DrawerLayout() = ThemeConsumer { theme ->
        styledDiv {
            css {
                width = props.portraitWidth
                backgroundColor = theme.backgroundVariantColor
                color = theme.onBackgroundVariantColor
                position = Position.fixed
                height = 100.pct
                top = 0.px
                left = 0.px
                transition(duration = 0.2.s)
                onDesktop {
                    width = props.landscapeWidth
                    if (!state.drawerOpen) {
                        left = -props.landscapeWidth
                    }
                }
                onMobile {
                    width = props.portraitWidth
                    if (!state.drawerOpen) {
                        left = -props.portraitWidth
                    }
                }
                onPaper {
                    display = Display.none
                }
            }
            SimpleBar {
                props.drawer(this)
            }
        }
    }

    private fun RBuilder.ContentLayout() = ThemeConsumer { theme ->
        styledDiv {
            css {
                backgroundColor = theme.backgroundColor
                color = theme.onBackgroundColor
                position = Position.fixed
                height = 100.pct
                right = 0.px
                top = 0.px
                transition(duration = 0.2.s)
                onPaper {
                    width = 100.pct
                }
                onDesktop {
                    width = 100.pct - props.landscapeWidth
                    if (!state.drawerOpen) {
                        width = 100.pct
                    }
                }
                onMobile {
                    width = 100.pct
                }
            }
            SimpleBar {
                props.content(this)
            }
        }
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            width = 100.pct
            height = 100.pct
        }
        ContentLayout()
        DrawerLayout()
    }
}

val navDrawerState = MutableStateFlow(false)

fun RBuilder.NavigationDrawer(
    drawer: RBuilder.() -> Unit,
    content: RBuilder.() -> Unit,
    drawerOpen: Boolean = false,
    portraitWidth: LinearDimension = 80.pct,
    landscapeWidth: LinearDimension = 20.pct
) {
    navDrawerState.value = drawerOpen
    child(NavigationDrawer::class.js, Props(
        drawer = drawer,
        content = content,
        portraitWidth = portraitWidth,
        landscapeWidth = landscapeWidth
    )) {}
}