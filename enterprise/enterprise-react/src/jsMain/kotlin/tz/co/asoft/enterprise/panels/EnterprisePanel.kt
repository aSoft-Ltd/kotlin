package tz.co.asoft.enterprise.panels

import kotlinx.css.*
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.router.dom.browserRouter
import styled.css
import styled.styledSection
import tz.co.asoft.AbstractModuleRoute
import tz.co.asoft.enterprise.panels.EnterprisePanel.Props
import tz.co.asoft.onDesktop
import tz.co.asoft.onMobile
import tz.co.asoft.onPaper

class EnterprisePanel : RComponent<Props, RState>() {
    class Props(
        val title: String,
        val footer: String,
        val openDrawer: Boolean,
        val menus: List<NavMenu>,
        val routes: List<AbstractModuleRoute<out RProps>>,
        val photoUrl: String,
        val onMenuClicked: () -> Unit
    ) : RProps

    private fun RBuilder.applicationSide(title: String) = styledSection {
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
        child(Application::class.js, Application.Props(
            title = title,
            photoUrl = props.photoUrl,
            routes = props.routes,
            onDrawerOpen = props.onMenuClicked
        )) {}
    }

    private fun RBuilder.navigationSide(
        footer: String,
        openDrawer: Boolean,
        menus: List<NavMenu>
    ) = styledSection {
        css {
            height = 100.pct
            onDesktop {
                width = 20.pct
            }
            transition(duration = 0.5.s)
            onMobile {
                position = Position.absolute
                left = if (openDrawer) 0.px else (-80).vw
                top = 0.px
                width = 80.vw
            }
            onPaper {
                display = Display.none
            }
        }

        child(NavPane::class.js, NavPane.Props(
            menus = menus,
            footer = footer
        )) { props.children() }
    }

    override fun RBuilder.render(): dynamic = styledSection {
        css {
            display = Display.flex
            height = 100.vh
            width = 100.pct
        }
        browserRouter {
            navigationSide(
                props.footer,
                props.openDrawer,
                props.menus
            )
            applicationSide(props.title)
        }
    }
}