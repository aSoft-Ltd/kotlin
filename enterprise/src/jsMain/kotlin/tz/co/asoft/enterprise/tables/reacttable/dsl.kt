package tz.co.asoft.enterprise.tables.reacttable

import kotlinext.js.jsObject
import kotlinext.js.require
import react.RBuilder
import react.RHandler
import react.ReactElement
import react.buildElement

private var isReactTableCssLoaded = false
fun <D : Any> Column<D>.access(trans: (D) -> Any) {
    id = "$Header-id"
    accessor = trans
}

fun <D : Any> Column<D>.render(builder: RBuilder.(D) -> ReactElement) {
    Cell = { row -> buildElement { builder(row.original) } }
}

internal fun <D : Any> RBuilder.reactTable(
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