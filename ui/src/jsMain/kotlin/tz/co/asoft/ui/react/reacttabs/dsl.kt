package tz.co.asoft.ui.react.reacttabs

import kotlinext.js.require
import react.RBuilder
import react.RElementBuilder
import react.RHandler
import react.RProps

fun RBuilder.tabs(handler: RHandler<TabsProps> = {}) = child(Tabs::class) {
    if (!isReactTabCssLoaded) {
        loadReactTabsCss()
    }
    handler()
}

fun RElementBuilder<TabListProps>.tab(handler: RHandler<RProps> = {}) = child(Tab::class, handler)
fun RElementBuilder<TabsProps>.tabList(handler: RHandler<TabListProps> = {}) = child(TabList::class, handler)
fun RElementBuilder<TabsProps>.tabPanel(handler: RHandler<RProps> = {}) = child(TabPanel::class, handler)

var isReactTabCssLoaded = false

private fun loadReactTabsCss() {
    require("react-tabs/style/react-tabs.css")
    isReactTabCssLoaded = true
}