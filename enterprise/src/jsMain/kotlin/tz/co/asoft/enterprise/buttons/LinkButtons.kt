package tz.co.asoft.enterprise.buttons

import kotlinext.js.jsObject
import react.*
import react.router.dom.useHistory
import tz.co.asoft.react.icons.IconProps

fun RBuilder.LinkTextButton(
    name: String,
    to: String,
    icon: RClass<IconProps>? = null
) = child(functionalComponent<RProps> {
    val history = useHistory()
    TextButton(name, icon) {
        history.push(to)
    }
}, jsObject())

fun RBuilder.LinkContainedButton(
    name: String,
    to: String,
    icon: RClass<IconProps>? = null
) = child(functionalComponent<RProps> {
    val history = useHistory()
    ContainedButton(name, icon) {
        history.push(to)
    }
}, jsObject())

fun RBuilder.LinkOutlinedButton(
    name: String,
    to: String,
    icon: RClass<IconProps>? = null
) = child(functionalComponent<RProps> {
    val history = useHistory()
    OutlinedButton(name, icon) {
        history.push(to)
    }
})