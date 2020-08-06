package tz.co.asoft

import kotlinext.js.require
import kotlinx.css.*
import react.RBuilder
import react.RHandler
import react.buildElement
import styled.css
import styled.styledDiv

fun <D : Any> RBuilder.FoldableTable(
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
    handler: RHandler<FoldableTableProps<D>>? = null
) = ThemeConsumer { theme ->
    val Table = foldableTableHOC<D>(ReactTable::class.js)
    if (!isReactTableCssLoaded) {
        require("react-table/react-table.css")
        isReactTableCssLoaded = true
    }

    styledDiv {
        css { +styles.table(theme) }
        Table {
            attrs.FoldIconComponent = { FoldIconComponent(it) }
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

private fun FoldIconComponent(props: FoldIconProps) = buildElement {
    styledDiv {
        css {
            display = Display.grid
            fontSize = 1.em
            padding(0.5.em)
            gridTemplateColumns = GridTemplateColumns("1fr")
            children {
                justifySelf = JustifyContent.center
            }
        }
        if (props.collapsed) FaChevronRight {} else FaChevronLeft {}
    }
}