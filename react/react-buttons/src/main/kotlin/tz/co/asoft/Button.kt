package tz.co.asoft

import react.RBuilder
import react.RClass

fun RBuilder.Button(
    name: String,
    type: UIType = UIType.Contained,
    icon: RClass<IconProps>? = null,
    onClick: (() -> Unit)? = null
) = when (type) {
    UIType.Contained -> ContainedButton(name, icon, onClick)
    UIType.Outlined -> OutlinedButton(name, icon, onClick)
    UIType.Text -> TextButton(name, icon, onClick)
}