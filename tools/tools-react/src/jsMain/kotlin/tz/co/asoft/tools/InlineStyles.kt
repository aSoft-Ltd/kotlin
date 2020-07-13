package tz.co.asoft.tools

import kotlinx.css.CSSBuilder
import react.RElementBuilder
import react.RProps
import styled.toStyle

external interface StyledProps : RProps {
    /**
     * Inline style tag
     */
    var style: dynamic
}

fun RElementBuilder<StyledProps>.css(builder: CSSBuilder.() -> Unit) {
    attrs.style = CSSBuilder().apply(builder).toStyle()
}