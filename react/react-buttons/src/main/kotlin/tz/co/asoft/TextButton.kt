package tz.co.asoft

import kotlinx.html.ButtonType
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RClass
import styled.css
import styled.styledButton

fun RBuilder.TextButton(name: String, icon: RClass<IconProps>? = null, onClick: (() -> Unit)? = null) = ThemeConsumer { theme ->
    styledButton {
        css { +styles.text(theme) }
        attrs.type = if (onClick == null) ButtonType.submit else ButtonType.button
        attrs.onClickFunction = {
            if (onClick != null) {
                it.preventDefault()
                onClick()
            }
        }
        attrs["data-name"] = name
        ButtonLayout(name, icon)
    }
}