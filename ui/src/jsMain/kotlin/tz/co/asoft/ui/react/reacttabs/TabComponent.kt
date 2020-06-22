package tz.co.asoft.ui.react.reacttabs

import react.*
import tz.co.asoft.ui.react.reacttabs.TabComponent.Props as Props

class TabComponent : RComponent<Props, RState>() {
    class Props : RProps {
        var sections = listOf<TabSection>()
    }

    override fun RBuilder.render(): dynamic = tabs {
        tabList {
            props.sections.forEach { tab { it.name } }
        }

        props.sections.forEach {
            tabPanel { it.builder(this) }
        }
    }
}

class TabSection(val name: String, val builder: RBuilder.() -> Unit)

fun RBuilder.tabComponent(handler: RHandler<Props>) = child(TabComponent::class.js, Props(), handler)