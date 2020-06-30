package tz.co.asoft.enterprise.buttons

import kotlinx.html.ButtonType
import kotlinx.html.js.onClickFunction
import react.RBuilder
import styled.css
import styled.styledButton
import tz.co.asoft.theme.ThemeConsumer

@Deprecated("Do not use this ")
fun RBuilder.SecondaryButton(name: String, onClick: () -> Unit) = ThemeConsumer { theme ->
    styledButton {
        css { +styles.secondary(theme) }
        attrs.type = ButtonType.button

        attrs.onClickFunction = {
            it.preventDefault()
            onClick()
        }
        attrs["data-name"] = name
        +name
    }
}