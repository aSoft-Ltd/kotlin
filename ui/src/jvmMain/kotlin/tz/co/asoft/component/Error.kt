package tz.co.asoft.component

import kotlinx.coroutines.launch
import tornadofx.button
import tornadofx.hbox
import tornadofx.label
import tornadofx.vbox
import tz.co.asoft.component.Error.Props
import tz.co.asoft.ui.action.Action

class Error : Component<Props, Any>() {
    class Props {
        var msg = ""
        var actions = listOf<Action>()
    }

    override fun render() = vbox {
        label(props.msg)
        hbox {
            props.actions.forEach { a ->
                button(a.name) {
                    setOnAction { launch { a.handler() } }
                }
            }
        }
    }
}