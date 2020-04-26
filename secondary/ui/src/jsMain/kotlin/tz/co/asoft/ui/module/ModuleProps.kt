package tz.co.asoft.ui.module

import tz.co.asoft.ui.theme.Theme
import react.router.dom.RouteResultProps
import tz.co.asoft.components.BaseModuleProps

open class ModuleProps : BaseModuleProps() {
    var theme = Theme()
    var themes = arrayOf<Theme>()
    var allPerms = arrayOf<String>()
    var setTitle = { _: String -> }
    var setTheme = { _: Theme -> }
    var modules = arrayOf<Module>()
    var routeProps: RouteResultProps<*>? = null
}