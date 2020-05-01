package tz.co.asoft.ui.react.composites.framework

import tz.co.asoft.ui.theme.Theme
import kotlinx.css.*
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import react.*
import react.router.dom.RouteResultHistory
import react.router.dom.hashRouter
import styled.css
import styled.styledDiv
import styled.styledSection
//import tz.co.asoft.auth.User
import tz.co.asoft.ui.module.Module
import tz.co.asoft.ui.module.Page
import tz.co.asoft.ui.module.WebsiteProps
import tz.co.asoft.ui.react.composites.framework.FrameworkComponent.Props
import tz.co.asoft.ui.react.composites.framework.FrameworkComponent.State
import tz.co.asoft.ui.react.composites.framework.applications.Application
import tz.co.asoft.ui.react.composites.framework.navmenu.NavPane
import tz.co.asoft.ui.react.tools.onDesktop
import tz.co.asoft.ui.react.tools.onMobile
import tz.co.asoft.ui.react.tools.onPaper
import kotlin.browser.window
import kotlin.reflect.KClass

class FrameworkComponent(p: Props) : RComponent<Props, State>(p) {
    private var history: RouteResultHistory? = null

    class Props : RProps {
        var themes = arrayOf<Theme>()
        var footer = "Footer"
        var title = "Title"
        var onSignOut = {}
        var showDashboard = false
        var modules = arrayOf<Module>()
        var pages = arrayOf<Page>()
        lateinit var website: KClass<*>
    }

    class State(props: Props) : RState {
        var theme = props.themes.getOrNull(0) ?: Theme()
        var title: String = "Dashboard"
        var selectedSection = props.modules[0].mainSection
        var drawerOpen = false
        var hasError = false
    }

    init {
        state = State(p)
    }

    private fun logout() = setState {
        history?.push("/")
        props.onSignOut()
    }

    private fun login() = setState {
        history?.push("/dashboard/")
    }

    override fun componentDidCatch(error: Throwable, info: RErrorInfo) {
        console.log(error.message)
        setState {
            hasError = true
        }
    }

    private fun RBuilder.navPane() = child(NavPane::class) {
        attrs {
            title = props.title
//            user = props.user!!
            theme = state.theme
            selectedSection = state.selectedSection
            footer = props.footer
            modules = props.modules
        }
        attrs.onCloseDrawer = {
            setState {
                drawerOpen = false
            }
        }
        attrs.onMenuItemClicked = {
            setState {
                drawerOpen = false
                selectedSection = it
            }
        }
    }

    private fun RBuilder.application() = child(Application::class) {
        attrs {
            theme = state.theme
            title = state.title
//            user = props.user!!
            modules = props.modules
            themes = props.themes
            setTheme = {
                window.setTimeout({
                    if (state.theme != it)
                        setState {
                            theme = it
                        }
                }, 0)
            }
            onRouteResultHistory = {
                history = it
            }
        }

        attrs.onDrawerOpen = {
            setState {
                drawerOpen = true
            }
        }

        attrs.onLogout = {
            logout()
        }
    }

    private fun RBuilder.navigationSide() = styledSection {
        css {
            height = 100.pct
            onDesktop {
                width = 20.pct
            }
            transition(duration = 0.5.s)
            onMobile {
                position = Position.absolute
                left = if (state.drawerOpen) {
                    0.px
                } else {
                    (-80).vw
                }
                top = 0.px
                width = 80.vw
            }
            onPaper {
                display = Display.none
            }
        }
        navPane()
    }

    private fun RBuilder.applicationSide() = styledSection {
        css {
            height = 100.pct
            onDesktop {
                width = 80.pct
            }
            onMobile {
                width = 100.pct
            }

            onPaper {
                width = 100.pct
            }
        }
        application()
    }

    private fun RBuilder.dashboardSection() = styledSection {
        css {
            display = Display.flex
            height = 100.vh
            width = 100.pct
        }
        navigationSide()
        applicationSide()
    }

    private fun RBuilder.anonymousPage() = child(props.website.unsafeCast<KClass<RComponent<Website.Props, RState>>>()) {
        attrs.theme = state.theme
        attrs.footer = props.footer
        attrs.pages = props.pages
        attrs.onLogin = { prop ->
            history = prop!!.history
            login()
        }
        attrs.onRouteResultHistory = {
            history = it
        }
    }

    override fun RBuilder.render(): dynamic {
        if (state.hasError) {
            return styledDiv {
                +"Opps Error Here"
            }
        }
        return hashRouter {
            if (!props.showDashboard) {
                anonymousPage()
            } else {
                dashboardSection()
            }
        }
    }
}

fun RBuilder.framework(handler: RHandler<Props> = {}) = child(FrameworkComponent::class) {
    attrs { website = Website::class.unsafeCast<KClass<RComponent<WebsiteProps, RState>>>() }
    handler()
}