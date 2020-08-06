package tz.co.asoft

import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.DIV
import kotlinx.html.js.onClickFunction
import react.*
import styled.StyledDOMBuilder
import styled.animation
import styled.css
import styled.styledDiv
import tz.co.asoft.Tabs.Props
import tz.co.asoft.Tabs.State

private class Tabs(p: Props) : RComponent<Props, State>(p) {
    class Props(
        val lineSize: LinearDimension,
        val beforeTabs: (StyledDOMBuilder<DIV>.() -> Unit)?,
        val tabs: List<Tab>
    ) : RProps

    class State(var currentTab: Tab, var tabs: List<Tab>) : RState

    init {
        state = State(
            currentTab = p.tabs.firstOrNull() ?: throw Exception("Tabs Component: Must have at least one tab"),
            tabs = props.tabs
        )
    }

    private fun RBuilder.TabHeader(tab: Tab) = ThemeConsumer { theme ->
        styledDiv {
            attrs.onClickFunction = { setState { currentTab = tab } }
            css {
                cursor = Cursor.pointer
                if (tab == state.tabs.firstOrNull() && props.beforeTabs == null) marginLeft = 0.em
                if (tab == state.tabs.lastOrNull()) marginRight = 0.em
                borderTopLeftRadius = 5.px
                borderTopRightRadius = 5.px
                transition(duration = 0.2.s)
                if (tab == state.currentTab) {
                    boxShadow(offsetY = props.lineSize, color = theme.surfaceColor)
                    borderLeft = "solid ${props.lineSize} ${theme.onSurfaceColor}"
                    borderTop = "solid ${props.lineSize} ${theme.onSurfaceColor}"
                    borderRight = "solid ${props.lineSize} ${theme.onSurfaceColor}"
                    after {
                        position = Position.absolute
                        left = -props.lineSize
                        right = props.lineSize
                        bottom = -props.lineSize
                        width = 100.pct + props.lineSize + props.lineSize
                        height = 100.pct
                        borderLeft = "solid ${props.lineSize} ${theme.onSurfaceColor}"
                        borderRight = "solid ${props.lineSize} ${theme.onSurfaceColor}"
                    }
                } else {
                    borderLeft = "solid ${props.lineSize} ${theme.surfaceColor}"
                    borderTop = "solid ${props.lineSize} ${theme.surfaceColor}"
                    borderRight = "solid ${props.lineSize} ${theme.surfaceColor}"
                }
            }

            Grid(cols = "auto" + if (tab.isCloseable) " auto" else "") {
                if (tab.icon != null) {
                    Grid(cols = "auto auto", gap = "0.2em") {
                        styledDiv { (tab.icon) {} }
                        styledDiv { +tab.name }
                    }
                } else {
                    styledDiv { +tab.name }
                }
                if (tab.isCloseable) styledDiv {
                    attrs.onClickFunction = {
                        if (state.tabs.size > 1) setState {
                            tabs -= tab
                            currentTab = tabs.first()
                        }
                    }
                    FaTimes {}
                }
            }
        }
    }

    override fun RBuilder.render(): dynamic = Grid(gap = "0em") { theme ->
        FlexBox {
            css {
                borderBottom = "solid ${props.lineSize} ${theme.onSurfaceColor}"
                children {
                    backgroundColor = theme.surfaceColor
                    color = theme.onSurfaceColor
                    position = Position.relative
                    userSelect = UserSelect.none
                    zIndex = 1
                    margin(horizontal = 0.1.em)
                    padding(horizontal = 0.3.em)
                    paddingTop = 0.3.em
                    paddingBottom = (-0.3).em
                    boxShadow(offsetY = -props.lineSize, color = theme.onSurfaceColor.withAlpha(0.2), blurRadius = 2.px, spreadRadius = (-1).px)
                }
            }
            props.beforeTabs?.let { it() }
            for (tab in state.tabs) TabHeader(tab)
        }
        Grid {
            css {
                backgroundColor = theme.surfaceColor
                color = theme.onSurfaceColor
                paddingTop = 0.5.em
            }
            state.currentTab.content.let { it(theme) }
        }
    }
}

fun RBuilder.Tabs(
    vararg tabs: Tab
) = child(Tabs::class.js, Props(2.px, null, tabs.toList())) {}

fun RBuilder.Tabs(
    lineSize: LinearDimension = 2.px,
    beforeTabs: (StyledDOMBuilder<DIV>.() -> Unit)? = null,
    tabs: List<Tab>
) = child(Tabs::class.js, Props(lineSize, beforeTabs, tabs)) {}