package tz.co.asoft.ui.react.composites.framework

import react.RBuilder
import react.RComponent
import react.RState
import react.ReactElement
import react.router.dom.RouteResultHistory
import react.router.dom.RouteResultProps
import react.router.dom.route
import tz.co.asoft.ui.module.Page
import tz.co.asoft.ui.module.WebsiteProps
import tz.co.asoft.ui.react.composites.framework.Website.Props

class Website : RComponent<Props, RState>() {
    object Props : WebsiteProps() {
        var onRouteResultHistory: (RouteResultHistory) -> Unit = { }
    }

    private fun Page.toReactComponent(): (RouteResultProps<WebsiteProps>) -> ReactElement = {
        it.match.params.apply {
            theme = props.theme
//            user = props.user
            footer = props.footer
            onLogin = { p ->
                props.onLogin(p)
            }
        }
        (component.unsafeCast<((RouteResultProps<WebsiteProps>) -> ReactElement)>()(it))
    }

    override fun RBuilder.render(): dynamic = props.pages.forEach { page ->
        route(path = page.route, exact = true, strict = true) { p: RouteResultProps<WebsiteProps> ->
            props.onRouteResultHistory(p.history)
            page.toReactComponent()(p)
        }
    }
}