@file:JsModule("react-tabs")
@file:JsNonModule

package tz.co.asoft.enterprise.tabs.reacttabs

import react.Component
import react.RProps
import react.RState

external interface TabsProps : RProps {
    var selectedIndex: Int
    var onSelect: (Int) -> Unit
}

external interface TabListProps : RProps

internal external class Tabs : Component<TabsProps, RState> {
    override fun render(): dynamic
}

internal external class Tab : Component<RProps, RState> {
    override fun render(): dynamic
}

internal external class TabList : Component<TabListProps, RState> {
    override fun render(): dynamic
}

internal external class TabPanel : Component<RProps, RState> {
    override fun render(): dynamic
}