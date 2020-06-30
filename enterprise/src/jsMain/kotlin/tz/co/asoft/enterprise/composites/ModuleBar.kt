package tz.co.asoft.enterprise.composites

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.boxShadow
import react.RBuilder
import react.dom.span
import styled.css
import styled.styledDiv
import tz.co.asoft.enterprise.text.SearchHandle
import tz.co.asoft.enterprise.text.SearchInput
import tz.co.asoft.theme.*
import tz.co.asoft.tools.justifySelf
import tz.co.asoft.tools.onDesktop
import tz.co.asoft.tools.onMobile
import tz.co.asoft.tools.onPaper

fun RBuilder.ModuleBar(
    title: RBuilder.() -> Unit,
    actions: (RBuilder.() -> Unit)? = null,
    searchHandle: SearchHandle? = null
) = ThemeConsumer { theme ->
    styledDiv {
        css {
            position = Position.sticky
            zIndex = 5
            top = 0.em
            padding(0.5.em)
            marginBottom = 1.em
            width = 100.pct
            minHeight = 3.5.em
            display = Display.grid
            gridTemplateColumns = GridTemplateColumns("2fr 1fr")
            onPaper { display = Display.none }
            backgroundColor = theme.surfaceColor
            color = theme.onSurfaceColor
            boxShadow(Color.gray, offsetY = 0.px, blurRadius = 5.px, spreadRadius = 1.px)
            children {
                alignSelf = Align.center
            }
        }

        styledDiv {
            css {
                display = Display.flex
                onDesktop {
                    justifyContent = JustifyContent.flexStart
                }
                onMobile {
                    width = 100.pct
                    justifyContent = JustifyContent.spaceBetween
                }
                flexWrap = FlexWrap.wrap
                children {
                    margin(horizontal = 0.5.em)
                    alignSelf = Align.center
                }
                a {
                    color = Color.inherit
                    fontSize = 1.3.em
                    textDecoration = TextDecoration.none
                }
            }
            span {
                title()
            }
            searchHandle?.let {
                SearchInput(
                    hint = it.hint,
                    css = {
                        minWidth = LinearDimension.auto
                        onMobile { width = 65.pct }
                    },
                    onSearch = it.onSearch
                )
            }
        }

        actions?.let {
            styledDiv {
                css {
                    justifySelf = JustifyContent.end
                    children { marginLeft = 1.em }
                }
                it()
            }
        }
    }
}