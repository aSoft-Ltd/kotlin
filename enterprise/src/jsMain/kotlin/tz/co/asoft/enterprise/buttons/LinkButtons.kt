package tz.co.asoft.enterprise.buttons

import react.RBuilder
import react.RClass
import react.RProps
import react.router.dom.useHistory
import tz.co.asoft.components.Component
import tz.co.asoft.react.icons.IconProps

fun RBuilder.LinkTextButton(name: String, to: String, icon: RClass<IconProps>? = null) = Component<RProps> {
    val history = useHistory()
    TextButton(name, icon) {
        history.push(to)
    }
}

fun RBuilder.LinkContainedButton(name: String, to: String, icon: RClass<IconProps>? = null) = Component<RProps> {
    val history = useHistory()
    ContainedButton(name, icon) {
        history.push(to)
    }
}

fun RBuilder.LinkOutlinedButton(name: String, to: String, icon: RClass<IconProps>? = null) = Component<RProps> {
    val history = useHistory()
    OutlinedButton(name, icon) {
        history.push(to)
    }
}