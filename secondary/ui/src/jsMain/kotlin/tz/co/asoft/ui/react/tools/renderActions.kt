package tz.co.asoft.ui.react.tools

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import react.RBuilder
import tz.co.asoft.tools.Action
import tz.co.asoft.ui.react.layout.columnLayout
import tz.co.asoft.ui.react.widget.button.primaryButton
import tz.co.asoft.ui.theme.Theme

fun <T> RBuilder.renderActions(scope: CoroutineScope, theme: Theme, actions: List<Action<T>>, param: T) = columnLayout(actions.joinToString(" ") { "1fr" }) {
    actions.forEach {
        primaryButton(it.name) {
            attrs.theme = theme
            attrs.onClick = { scope.launch { it.handler(param) } }
        }
    }
}