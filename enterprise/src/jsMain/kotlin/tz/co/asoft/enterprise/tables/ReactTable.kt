package tz.co.asoft.enterprise.tables

import kotlinext.js.jsObject
import kotlinx.css.*
import react.RBuilder
import react.RHandler
import react.ReactElement
import styled.StyleSheet
import styled.css
import styled.styledDiv
import tz.co.asoft.enterprise.buttons.ContainedButton
import tz.co.asoft.enterprise.buttons.OutlinedButton
import tz.co.asoft.enterprise.tables.reacttable.*
import tz.co.asoft.theme.Theme
import tz.co.asoft.theme.ThemeConsumer
import tz.co.asoft.theme.dropdown_clazz

fun <D : Any> RBuilder.ReactTable(
    data: List<D>,
    columns: List<Column<D>>,
    actions: (RBuilder.(D) -> Unit)? = null,
    handler: RHandler<TableProps<D>>? = null
) = ThemeConsumer { theme ->
    val actionColumn = if (actions != null) {
        jsObject<Column<D>> {
            Header = "Actions"
            render {
                val b = RBuilder().apply { actions(it) }
                styledDiv {
                    css {
                        display = Display.grid
                        gridTemplateColumns = GridTemplateColumns(b.childList.joinToString(" ") { "1fr" })
                        gap = Gap("1em")
                        width = 100.pct
                    }
                    actions(it)
                }
            }
        }
    } else null
    styledDiv {
        css { +styles.table(theme) }
        reactTable(data.toTypedArray(), (columns + listOf(actionColumn).mapNotNull { it }).toTypedArray()) { handler?.let { it() } }
    }
}

private object styles : StyleSheet("themed-react-table") {
    private val follow_theme by css {
        backgroundColor = Color.transparent
        color = Color.inherit
    }

    fun table(theme: Theme): RuleSet = {
        color = Color.inherit
        child("div .rt-noData") {
            +follow_theme
        }

        child("div .pagination-bottom .-pagination .-center .-pageInfo .-pageJump input") {
            +follow_theme
        }

        child("div .pagination-bottom .-pagination .-center span select") {
            +follow_theme
            +theme.dropdown_clazz
        }
    }
}