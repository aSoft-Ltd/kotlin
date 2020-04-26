package tz.co.asoft.ui.state

import tz.co.asoft.persist.tools.Cause
import tz.co.asoft.ui.action.Action

fun loading(msg: String = "Loading . . ") = UI.Loading(msg)

fun error(msg: String, actions: List<Action> = listOf()) = UI.Error(msg, actions)

fun Cause.toErrorUI(preMsg: String = "", actions: List<Action> = listOf()) = error("$preMsg: $message", actions)