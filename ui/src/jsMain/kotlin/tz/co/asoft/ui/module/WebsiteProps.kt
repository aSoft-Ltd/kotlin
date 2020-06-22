package tz.co.asoft.ui.module

import react.RProps
import react.router.dom.RouteResultProps

//import tz.co.asoft.auth.User

abstract class WebsiteProps : ModuleProps() {
    var footer = ""

    var onLogin = { _: RouteResultProps<RProps>? -> }
    var pages = arrayOf<Page>()
}