package tz.co.asoft.ui.react.composites.framework.applications

import tz.co.asoft.ui.theme.main
import tz.co.asoft.ui.react.composites.framework.applications.Application.Props
import tz.co.asoft.ui.react.composites.framework.applications.Application.State
import kotlinx.css.*
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLDivElement
import react.*
import react.router.dom.RouteResultHistory
import react.router.dom.RouteResultProps
import react.router.dom.route
import styled.css
import styled.styledDiv
import styled.styledImg
import styled.styledSection
import tz.co.asoft.ui.module.Module
import tz.co.asoft.ui.module.ModuleProps
import tz.co.asoft.ui.module.ScopedRComponent
import tz.co.asoft.ui.react.icons.reacticons.faBars
import tz.co.asoft.ui.react.tools.onDesktop
import tz.co.asoft.ui.react.tools.onMobile
import tz.co.asoft.ui.react.tools.onPaper
import kotlin.browser.document

class Application : ScopedRComponent<Props, State>() {
    companion object Props : ModuleProps() {
        var onDrawerOpen = {}
        var onLogout = {}
        var title = "Module Name"
        var userName = "username"
        var userPhoto = ""
        var onRouteResultHistory: (RouteResultHistory) -> Unit = { }
    }

    class State : RState

    private val APP_BAR_ID = "app-bar"

    private fun getPermissions(): Array<String> {
        var perms = arrayOf<String>()
        props.modules.forEach { module ->
            module.permits.forEach { perm ->
                if (!perms.contains(perm))
                    perms += perm
            }
        }
        return perms
    }

    private fun RBuilder.appBar() = styledSection {
        styledDiv {
            css {
                display = Display.flex
                alignItems = Align.center
                justifyContent = JustifyContent.spaceBetween
                height = 2.5.em
                onMobile {
                    height = 4.em
                }
                onPaper {
                    display = Display.none
                }
                width = 100.pct
                backgroundColor = Color(props.theme.primaryColor.main)
                color = Color(props.theme.text.onPrimary.main)
                borderBottom = "solid 2px ${props.theme.tertiaryColor.main}"
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
                    faBars {}
                    attrs.onClickFunction = {
                        props.onDrawerOpen()
                    }
                }

                styledDiv {
                    attrs {
                        id = APP_BAR_ID
                    }
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
                        marginRight = 0.5.em
                        fontSize = 1.em
                        onMobile {
                            display = Display.none
                        }
                    }
                    +props.userName
//                    +props.user.name
                }

                styledDiv {
                    css {
                        backgroundColor = props.theme.text.onPrimary.main()
                        borderRadius = 50.pct
                        +ApplicationSideStyles.userImage
                    }

                    if (props.userPhoto.isNotEmpty()) {
                        styledImg(src = props.userPhoto) {
                            css { +ApplicationSideStyles.userImage }
                        }
                    }

                    attrs.onClickFunction = {
                        props.onLogout()
                    }
                }
            }
        }
    }

    private fun Module.Section.toComponent(): (RouteResultProps<ModuleProps>) -> ReactElement? = {
        it.match.params.apply {
            theme = props.theme
            themes = props.themes
//            user = props.user

            allPerms = getPermissions()

            modules = props.modules

            routeProps = it

            setTitle = { title ->
                (document.getElementById(APP_BAR_ID).unsafeCast<HTMLDivElement?>())?.innerText = title
            }

            setTheme = props.setTheme
        }
        (component.unsafeCast<((RouteResultProps<ModuleProps>) -> ReactElement)>()(it))
    }

    private fun RBuilder.loadModule(module: Module) {
        val mods = module.sections.toMutableList()
        mods.add(0, module.mainSection)
        mods.filter { it.show() }.forEach { section ->
            route("/dashboard${section.route}", true, true) { routeProps: RouteResultProps<ModuleProps> ->
                props.onRouteResultHistory(routeProps.history)
                section.toComponent()(routeProps)
            }
        }
    }

    private fun RBuilder.contentSection() = styledSection {
        css {
            height = 100.pct - 2.5.em - 2.px
            width = 100.pct
            overflowX = Overflow.hidden
            overflowY = Overflow.scroll
            onPaper {
                height = LinearDimension.auto
            }
        }

        props.modules.forEach {
            loadModule(it)
        }
    }

    override fun RBuilder.render(): dynamic = styledSection {
        css {
            position = Position.relative
            backgroundColor = Color(props.theme.backgroundColor.light)
            height = 100.pct
        }
        appBar()
        contentSection()
    }
}