package tz.co.asoft.enterprise.tables

import kotlinx.css.*
import react.RBuilder
import styled.css
import styled.styledDiv
import tz.co.asoft.enterprise.buttons.TextButton
import tz.co.asoft.tools.Action

fun <T> RBuilder.TableActions(actions: List<Action<T>>, param: T) = styledDiv {
    css {
        display = Display.grid
        gridTemplateColumns = GridTemplateColumns(actions.joinToString(" ") { "1fr" })
        gap = Gap("1em")
        width = 100.pct
    }
    actions.forEach {
        TextButton(it.name) { it.handler(param) }
    }
}