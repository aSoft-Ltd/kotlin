package tz.co.asoft.ui.react.composites.framework.navmenu

import tz.co.asoft.ui.theme.Theme
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.js.onClickFunction
import react.*
import react.router.dom.routeLink
import styled.css
import styled.styledDiv
//import tz.co.asoft.auth.usecase.permissions.hasPermits
import tz.co.asoft.ui.module.Module
import tz.co.asoft.ui.module.ModuleProps
import tz.co.asoft.ui.react.composites.framework.navmenu.NavMenuComponent.Props
import tz.co.asoft.ui.react.composites.framework.navmenu.NavMenuComponent.State

class NavMenuComponent : RComponent<Props, State>() {
    class Props : ModuleProps() {
        lateinit var module: Module
        var onClick = { _: Module.Section -> }
        lateinit var selectedSection: Module.Section
    }

    class State : RState {
        var isExpanded = false
    }

    init {
        state = State()
    }

    private val module get() = props.module
    private val menus: List<Module.Section>
        get() {
            state.isExpanded = state.isExpanded && module.isSelected()
            return module.sections.filter { it.isMenu }
        }

    private fun Module.isSelected(): Boolean {
        var selected = false
        sections.forEach {
            if (it == props.selectedSection) {
                selected = true
                return@forEach
            }
        }
        if (!selected) {
            selected = mainSection == props.selectedSection
        }
        return selected
    }

    private fun RBuilder.mainSection() = routeLink(to = "/dashboard${module.mainSection.route}") {
        styledDiv {
            css {
                textDecoration = TextDecoration.none
            }
            attrs.onClickFunction = {
                props.onClick(module.mainSection)
                it.stopPropagation()
            }
            css {
                width = 100.pct
                height = 100.pct
                display = Display.flex
                alignItems = Align.center
                padding(horizontal = 1.em)
                textDecoration = TextDecoration.none
                justifyContent = JustifyContent.spaceBetween
                if (!state.isExpanded && module.isSelected()) {
                    backgroundColor = Color(props.theme.secondaryColor.main)
                    color = Color(props.theme.text.onSecondary.main)
                }

                hover {
                    backgroundColor = Color(props.theme.secondaryColor.dark)
                    color = Color(props.theme.text.onSecondary.dark)
                }
            }

            styledDiv {
                css {
                    textDecoration = TextDecoration.none
                    i {
                        marginRight = 1.em
                    }
                }
                +module.mainSection.name
            }

            if (menus.isNotEmpty())
                styledDiv {
                    css {
                        transform {
                            rotateZ(if (state.isExpanded) 90.deg else 0.deg)
                        }
                        transition(duration = 0.2.s)
                        padding(1.em)
                    }

                    attrs.onClickFunction = {
                        setState { isExpanded = !isExpanded }
                    }
                }
        }
    }

    private fun RBuilder.subSections() = menus.forEach { section ->
        routeLink(to = "/dashboard${section.route}") {
            styledDiv {
                attrs.onClickFunction = {
                    props.onClick(section)
                    it.stopPropagation()
                }
                css {
                    width = 100.pct
                    height = if (state.isExpanded) 100.pct else 0.px
                    display = Display.flex
                    alignItems = Align.center
                    fontSize = 0.9.em
                    paddingLeft = 3.5.em
                    justifyContent = JustifyContent.spaceBetween
                    transition(duration = 0.2.s)
                    if (section == props.selectedSection) {
                        backgroundColor = Color(props.theme.secondaryColor.main)
                        color = Color(props.theme.text.onSecondary.main)
                    }

                    hover {
                        backgroundColor = Color(props.theme.secondaryColor.dark)
                        color = Color(props.theme.text.onSecondary.dark)
                    }
                }

                styledDiv {
                    +section.name
                }
            }
        }
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            display = Display.flex
            alignItems = Align.center
            minHeight = 3.em
            height = if (state.isExpanded) ((menus.size + 1) * 3).em else 3.em
            overflowY = Overflow.hidden
            justifyContent = JustifyContent.start
            flexDirection = FlexDirection.column
            fontSize = 0.9.em
            width = 100.pct
            cursor = Cursor.pointer
            textDecoration = TextDecoration.none
            borderTop = "solid 1px ${props.theme.text.onAlternate[0].main}"
            transition(duration = 0.2.s)

            a {
                textDecoration = TextDecoration.none
                color = Color.inherit
                width = 100.pct
                height = 3.em
                minHeight = 3.em
                if (state.isExpanded)
                    borderTop = "solid 1px ${props.theme.text.onAlternate[0].main}40"
                display = Display.flex
                alignItems = Align.center
                justifyContent = JustifyContent.spaceBetween
            }
        }

        mainSection()

        subSections()
    }
}

fun RBuilder.navMenu(module: Module, handler: RHandler<Props> = {}) =
    child(NavMenuComponent::class) {
        attrs.module = module
        attrs {
            theme = Theme()
            onClick = {}
        }
        handler()
    }
