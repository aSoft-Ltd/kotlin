package tz.co.asoft.enterprise.tabs.reacttabs

import kotlinx.css.CSSBuilder
import kotlinx.css.Color
import kotlinx.css.backgroundColor
import org.w3c.dom.HTMLLIElement
import react.RBuilder
import styled.toStyle
import tz.co.asoft.theme.Theme
import tz.co.asoft.theme.ThemeConsumer
import tz.co.asoft.theme.onSurfaceColor
import tz.co.asoft.theme.surfaceColor
import tz.co.asoft.tools.By
import tz.co.asoft.tools.find
import kotlin.browser.document
import kotlin.browser.window

class TabInfo(val name: String, val builder: RBuilder.() -> Unit)

private var lastSelected: HTMLLIElement? = null
private fun setStyles(theme: Theme) = window.setTimeout({
    lastSelected?.style?.apply {
        borderColor = "transparent"
    }
    lastSelected = document.body?.find<HTMLLIElement>(By.className("react-tabs__tab--selected"))?.apply {
        style.apply {
            backgroundColor = Color.transparent.value
            color = Color.inherit.value
            borderColor = theme.onSurfaceColor.value
        }
    }
}, 0)

fun RBuilder.Tabs(vararg tabs: TabInfo) = ThemeConsumer { theme ->
    Tabs {
        attrs.onSelect = { setStyles(theme) }
        attrs.asDynamic()["style"] = CSSBuilder().apply {
            backgroundColor = theme.surfaceColor
        }.toStyle()

        TabList {
            tabs.forEach { tab ->
                Tab { +tab.name }
            }
        }

        tabs.forEach {
            TabPanel { it.builder(this) }
        }
    }
    setStyles(theme)
}