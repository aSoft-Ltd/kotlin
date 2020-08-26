package tz.co.asoft

import react.createContext
import kotlinx.coroutines.flow.MutableStateFlow
import react.RBuilder
import react.createContext
import tz.co.asoft.DrawerState
import tz.co.asoft.isDesktop

enum class DrawerState {
    Opened, Closed
}

val MainDrawerControllerContext by lazy {
    createContext(MutableStateFlow(if (isDesktop) DrawerState.Opened else DrawerState.Closed))
}

fun RBuilder.MainDrawerControllerConsumer(
    builder: RBuilder.(MutableStateFlow<DrawerState>) -> Unit
) = MainDrawerControllerContext.Consumer(builder)