package tz.co.asoft.ui.state

import tz.co.asoft.ui.action.Action

sealed class UI {
    class Loading(val msg: String = "Loading . . .") : UI()
    open class Showing : UI()
    class Error(val msg: String = "Unknown Error", val actions: List<Action> = listOf()) : UI()
}