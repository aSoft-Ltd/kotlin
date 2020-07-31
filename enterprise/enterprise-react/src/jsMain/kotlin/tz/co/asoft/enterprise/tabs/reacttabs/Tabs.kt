package tz.co.asoft.enterprise.tabs.reacttabs

import kotlinx.css.CSSBuilder
import kotlinx.css.Color
import kotlinx.css.backgroundColor
import org.w3c.dom.HTMLLIElement
import react.RBuilder
import styled.toStyle
import tz.co.asoft.*
import kotlin.browser.document
import kotlin.browser.window

class TabInfo(val name: String, val builder: RBuilder.() -> Unit)

private var lastSelected = listOf<HTMLLIElement>()
private fun setStyles(theme: Theme) = window.setTimeout({
    lastSelected.forEach { it.style.borderColor = "transparent" }

    (document.body?.findAll<HTMLLIElement>(By.className("react-tabs__tab--selected")) ?: listOf()).map {
        it.style.apply {
            backgroundColor = Color.transparent.value
            color = Color.inherit.value
            borderColor = theme.onSurfaceColor.value
        }
        it
    }.also { lastSelected = it }
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