package tz.co.asoft.ui.react.composites.modulebar

import tz.co.asoft.ui.theme.Theme
import tz.co.asoft.ui.theme.light
import tz.co.asoft.ui.tools.throttle
import kotlinx.css.*
import kotlinx.css.properties.boxShadow
import react.*
import styled.css
import styled.styledDiv
import tz.co.asoft.ui.react.composites.modulebar.ModuleBar.Props
import tz.co.asoft.ui.react.tools.onDesktop
import tz.co.asoft.ui.react.tools.onMobile
import tz.co.asoft.ui.react.tools.onPaper
import tz.co.asoft.ui.react.widget.text.searchbar.search
import tz.co.asoft.ui.react.widget.text.searchbar.searchBox

class ModuleBar(p: Props) : RComponent<Props, RState>(p) {
    object Props : RProps {
        var theme = Theme()
        var searchHint = "Type to search"
        var onSearch = { _: String -> }
        var css: CSSBuilder.() -> Unit = {}
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            position = Position.sticky
            zIndex = 5
            top = 0.em
            padding(0.5.em)
            margin((-0.5).em)
            marginBottom = 1.em
            width = 100.pct + 1.em
            display = Display.flex
            justifyContent = JustifyContent.spaceBetween
            onMobile {
                flexWrap = FlexWrap.wrap
            }
            onPaper {
                display = Display.none
            }
            alignItems = Align.center
            backgroundColor = props.theme.backgroundColor.light()
            color = props.theme.text.onBackground.light()
            boxShadow(Color.gray, offsetY = 2.px, blurRadius = 10.px, spreadRadius = 1.px)
            +props.css
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
                }
                firstChild {
                    marginLeft = 0.em
                }
            }
            props.children()
        }

        styledDiv {
            css {
                display = Display.flex
                padding(0.2.em)
                onDesktop {
                    justifyContent = JustifyContent.flexEnd
                }
                onMobile {
                    width = 100.pct
                    justifyContent = JustifyContent.center
                }
            }
            search {
                attrs {
                    theme = props.theme
                    hint = props.searchHint
                }
                attrs.onSearch = { key ->
                    throttle(500L) {
                        props.onSearch(key)
                    }
                }
            }
        }
    }
}

fun RBuilder.moduleBar(handler: RHandler<Props>) = child(ModuleBar::class.js, Props, handler)