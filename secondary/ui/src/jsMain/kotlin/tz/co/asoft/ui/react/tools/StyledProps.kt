package tz.co.asoft.ui.react.tools

import tz.co.asoft.ui.theme.Theme
import kotlinx.css.CSSBuilder
import react.RElementBuilder
import react.RProps

abstract class StyledProps : RProps {
    var css: CSSBuilder.() -> Unit = {}
    val data = mutableMapOf<String, Any>()
    var onClick = {}
}

fun <P : StyledProps> RElementBuilder<P>.css(builder: CSSBuilder.() -> Unit) {
    attrs.css = {
        builder()
    }
}

abstract class ThemedProps : StyledProps() {
    var theme = Theme()
}

interface IStyled : RProps {
    var css: CSSBuilder.() -> Unit
}

fun <P : IStyled> RElementBuilder<P>.css(builder: CSSBuilder.() -> Unit) {
    attrs.css = {
        builder()
    }
}