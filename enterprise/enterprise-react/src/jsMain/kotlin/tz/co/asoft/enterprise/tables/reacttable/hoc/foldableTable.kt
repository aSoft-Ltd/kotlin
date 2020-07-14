@file:JsModule("react-table/lib/hoc/foldableTable")

package tz.co.asoft.enterprise.tables.reacttable.hoc

import react.RClass
import react.ReactElement
import tz.co.asoft.enterprise.tables.reacttable.ReactTable
import tz.co.asoft.enterprise.tables.reacttable.TableProps


external interface FoldIconProps {
    val collapsed: Boolean
    val header: String
    val icon: String
    val onClick: () -> Unit
}

external interface FoldableTableProps<D : Any> : TableProps<D> {
    var FoldIconComponent: (FoldIconProps) -> ReactElement
    var FoldButtonComponent: (FoldIconProps) -> ReactElement
}

@JsName("default")
internal external fun <D : Any> foldableTableHOC(clazz: JsClass<ReactTable<*>>): RClass<FoldableTableProps<D>>