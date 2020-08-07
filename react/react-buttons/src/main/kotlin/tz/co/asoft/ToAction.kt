package tz.co.asoft

import react.RBuilder

fun <T> RBuilder.Button(action: AButton<T>, param: T) = when (action) {
    is AButton.Contained -> ContainedButton(action.name, action.icon) { action.handler(param) }
    is AButton.Outlined -> OutlinedButton(action.name, action.icon) { action.handler(param) }
    is AButton.Text -> TextButton(action.name, action.icon) { action.handler(param) }
}