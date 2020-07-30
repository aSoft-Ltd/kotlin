package tz.co.asoft.enterprise.action

import react.RBuilder
import tz.co.asoft.enterprise.buttons.ContainedButton
import tz.co.asoft.enterprise.buttons.OutlinedButton
import tz.co.asoft.enterprise.buttons.TextButton

fun <T> RBuilder.ActionButton(action: AButton<T>, param: T) = when (action) {
    is AButton.Contained -> ContainedButton(
        name = action.name,
        icon = action.icon
    ) { action.handler(param) }
    is AButton.Outlined -> OutlinedButton(
        name = action.name,
        icon = action.icon
    ) { action.handler(param) }
    is AButton.Text -> TextButton(
        name = action.name,
        icon = action.icon
    ) { action.handler(param) }
}