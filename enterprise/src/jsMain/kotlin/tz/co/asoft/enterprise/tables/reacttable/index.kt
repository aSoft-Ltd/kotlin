@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "NESTED_CLASS_IN_EXTERNAL_INTERFACE")
@file:JsModule("react-table")
@file:JsNonModule

package tz.co.asoft.enterprise.tables.reacttable

import org.w3c.dom.Node
import react.*

external interface Resize {
    var id: String
    var value: Any
}

external interface Filter {
    var id: String
    var value: String
    var pivotId: String? get() = definedExternally; set(value) = definedExternally
}

external interface SortingRule {
    var id: String
    var desc: Boolean
}

@JsName("default")
external class ReactTable<D : Any> : Component<TableProps<D>, RState> {
    override fun render(): dynamic
}

external interface TableProps<D : Any> : RProps {
    var data: Array<D>
    var loading: Boolean
    var showPagination: Boolean
    var showPaginationTop: Boolean
    var showPaginationBottom: Boolean
    var manual: Boolean
    var multiSort: Boolean
    var showPageSizeOptions: Boolean
    var pageSizeOptions: Array<Int>
    var defaultPageSize: Int
    var minRows: Number?
    var showPageJump: Boolean
    var sortable: Boolean
    var collapseOnSortingChange: Boolean
    var collapseOnPageChange: Boolean
    var collapseOnDataChange: Boolean
    var freezeWhenExpanded: Boolean
    var defaultSorting: Array<SortingRule>
    var showFilters: Boolean
    var defaultFiltering: Array<Filter>
    var defaultFilterMethod: (filter: Filter, rows: Row<D>, column: Column<D>) -> Boolean
    var defaultSortMethod: (a: Any, b: Any, desc: Any) -> Number
    var resizable: Boolean
    var filterable: Boolean
    var defaultResizing: Array<Resize>
    var defaultSortDesc: Boolean
    var defaultSorted: Array<SortingRule>
    var defaultFiltered: Array<Filter>
    var defaultResized: Array<Resize>
    var defaultExpanded: Any
    var onChange: (value: Any? /*= null*/) -> Unit
    var className: String
    var style: Any
    var column: Any?
    var columns: Array<Column<D>>
    var expanderDefaults: Any?
    var pivotDefaults: Any?
    var PadRowComponent: () -> Node
    var onFetchData: (state: Any, instance: Any) -> Unit
    var getTrProps: (state: dynamic, row: Row<D>) -> TrResult
}

external interface FilterProp {
    var filter: Filter?
    var onChange: (String) -> Unit
}

external interface Column<D : Any> {
    var Header: dynamic
    var accessor: dynamic
    var id: String
    var aggregate: ((values: Any, rows: Any) -> Any)? get() = definedExternally; set(value) = definedExternally
    var width: Number
    var maxWidth: Number
    var expander: Boolean
    var columns: Array<Column<*>>
    var filterable: Boolean
    var foldable: Boolean
    var Filter: (FilterProp) -> ReactElement
    var filterMethod: (filter: Filter, row: Row<D>, column: Column<D>) -> Boolean
    var pivot: Boolean
    var Cell: (Row<D>) -> ReactElement?
    var style: dynamic
}

external interface TrResult {
    var style: dynamic
    var onClick: () -> Unit
}

external interface Row<D> {
    var original: D
    var value: String
}