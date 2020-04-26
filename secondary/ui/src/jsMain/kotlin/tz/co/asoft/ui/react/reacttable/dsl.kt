package tz.co.asoft.ui.react.reacttable

import tz.co.asoft.ui.react.tools.isDesktop
import kotlinext.js.jsObject
import kotlinext.js.require
import react.RBuilder
import react.RHandler
import react.ReactElement
import react.buildElement
import kotlin.browser.window

private var isReactTableCssLoaded = false
fun <D : Any> Column<D>.access(trans: (D) -> String) {
    id = "$Header-id"
    accessor = trans
}

fun <D : Any> Column<D>.render(builder: RBuilder.(D) -> ReactElement) {
    Cell = { row -> buildElement { builder(row.original) } }
}

fun <D : Any> col(builder: Column<D>.() -> Unit) = jsObject<Column<D>> { }.apply(builder)

val tableWidth get() = (window.screen.availWidth * if (isDesktop) 0.8 else 1.0) * 0.99

fun <D : Any> RBuilder.reactTable(
        data: Array<D> = arrayOf(),
        columns: Array<Column<D>> = arrayOf(),
        handler: RHandler<TableProps<D>>
) = child(ReactTable::class.js, jsObject<TableProps<D>> { }) {
    if (!isReactTableCssLoaded) {
        require("react-table/react-table.css")
        isReactTableCssLoaded = true
    }
    attrs.defaultPageSize = 15
    attrs.data = data
    attrs.columns = columns
    handler()
}