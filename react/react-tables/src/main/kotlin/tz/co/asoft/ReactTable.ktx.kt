package tz.co.asoft

import kotlinext.js.jsObject
import kotlinext.js.require
import kotlinx.css.*
import react.RBuilder
import react.RHandler
import react.ReactElement
import react.buildElement
import styled.css
import styled.styledDiv

internal var isReactTableCssLoaded = false
fun <D : Any> Column<D>.access(trans: (D) -> Any) {
    id = "$Header"
    accessor = trans
}

inline fun <D : Any> column(name: String, builder: Column<D>.() -> Unit): Column<D> = jsObject {
    Header = name
    foldable = true
    onFilter { key, rowContent -> rowContent.contains(key, ignoreCase = true) }
    builder()
}

fun <D : Any> Column(name: String, trans: (D) -> Any) =
    column<D>(name = name, builder = { access(trans) })

inline operator fun Row<*>.get(key: String): String = this.asDynamic()[key]

inline fun <D : Any> Column<D>.render(crossinline builder: RBuilder.(D) -> ReactElement) {
    Cell = { row -> buildElement { builder(row.original) } }
}

inline fun <D : Any> RenderColumn(name: String, crossinline builder: RBuilder.(D) -> ReactElement) =
    column<D>(name) {
        filterable = false
        render(builder)
    }

inline fun Column<*>.filterComponent(crossinline builder: RBuilder.(FilterProp) -> ReactElement) {
    Filter = { buildElement { builder(it) } }
}

inline fun Column<*>.onFilter(crossinline handler: (key: String, rowContent: String) -> Boolean) {
    filterMethod = { filter, row, _ -> handler(filter.value, row[filter.pivotId ?: filter.id]) }
}

fun <D : Any> RBuilder.ReactTable(
    data: List<D>,
    columns: List<Column<D>>,
    actions: List<AButton<D>>? = null,
    showPagination: Boolean = true,
    showPaginationTop: Boolean = false,
    showPaginationBottom: Boolean = true,
    showPageSizeOptions: Boolean = true,
    pageSizeOptions: Array<Int> = arrayOf(10, 25, 50, 100),
    defaultPageSize: Int = 15,
    sortable: Boolean = true,
    resizable: Boolean = true,
    filterable: Boolean = true,
    handler: RHandler<TableProps<D>>? = null
) = ThemeConsumer { theme ->
    if (!isReactTableCssLoaded) {
        require("react-table/react-table.css")
        isReactTableCssLoaded = true
    }

    styledDiv {
        css { +styles.table(theme) }
        child(ReactTable::class.js, jsObject<TableProps<D>> {}) {
            attrs.data = data.toTypedArray()
            attrs.columns = columns + actions
            attrs.showPaginationTop = showPaginationTop
            attrs.showPaginationBottom = showPaginationBottom
            attrs.showPagination = showPagination
            attrs.showPageSizeOptions = showPageSizeOptions
            attrs.pageSizeOptions = pageSizeOptions
            attrs.defaultPageSize = defaultPageSize
            attrs.sortable = sortable
            attrs.resizable = resizable
            attrs.filterable = filterable
            handler?.invoke(this)
        }
    }
}

operator fun <D : Any> List<Column<D>>.plus(actions: List<AButton<D>>?) = if (actions == null) {
    toTypedArray()
} else {
    val actionsColumn = RenderColumn<D>("Actions") {
        styledDiv {
            css {
                display = Display.grid
                width = 100.pct
                gridTemplateColumns =
                    GridTemplateColumns(actions.joinToString(separator = " ") { "1fr" })
            }
            for (action in actions) Button(action, it)
        }
    }
    (this + actionsColumn).toTypedArray()
}