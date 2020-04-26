package tz.co.asoft.ui.module

abstract class Page {
    abstract val name: String
    abstract val author: String
    abstract val version: String
    open var init = {}
    open var icon = "cloud"
    abstract val route: String
    abstract val component: Any
}