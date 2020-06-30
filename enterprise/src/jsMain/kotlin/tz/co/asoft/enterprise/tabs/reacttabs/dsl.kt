package tz.co.asoft.enterprise.tabs.reacttabs

import kotlinext.js.require
import react.RBuilder
import react.RElementBuilder
import react.RHandler
import react.RProps

private var isReactTabCssLoaded = false

private fun loadReactTabsCss() {
    require("react-tabs/style/react-tabs.css")
    isReactTabCssLoaded = true
}

fun RBuilder.Tabs(handler: RHandler<TabsProps>) = child(Tabs::class) {
    if (!isReactTabCssLoaded) {
        loadReactTabsCss()
    }
    handler()
}

fun RElementBuilder<TabListProps>.Tab(handler: RHandler<RProps>) = child(Tab::class, handler)
fun RElementBuilder<TabsProps>.TabList(handler: RHandler<TabListProps>) = child(TabList::class, handler)
fun RElementBuilder<TabsProps>.TabPanel(handler: RHandler<RProps>) = child(TabPanel::class, handler)