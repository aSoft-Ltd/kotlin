package tz.co.asoft

import react.useMemo
import kotlin.reflect.KProperty

fun <V : VModel<*, *>> useViewModel(builder: () -> V): V = useMemo(builder, arrayOf())

operator fun <S> VModel<*, S>.getValue(thisRef: Any?, property: KProperty<*>): S = ui.asState()