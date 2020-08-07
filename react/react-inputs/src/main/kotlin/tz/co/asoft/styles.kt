package tz.co.asoft

import kotlinx.css.Display
import kotlinx.css.*
import kotlinx.css.Color
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import styled.StyleSheet

internal object styles : StyleSheet("asoft-react-inputs-styles") {
    val root by css {
        display = Display.inlineBlock
        position = Position.relative
        backgroundColor = Color.transparent
        width = 100.pct
        border = "none"
        marginTop = 1.em
        marginBottom = 1.em

        before {
            transition(duration = .2.s)
            position = Position.absolute
            height = 1.px
            left = 5.pct
            bottom = 0.px
            backgroundColor = Color.black
        }

        hover {
            before {
                left = 0.pct
                height = 2.px
            }
        }

        focus {
            left = 0.pct
            height = 2.px
        }
    }

    val input by css {
        transition(duration = .2.s)
        position = Position.relative
        backgroundColor = Color.transparent
        minHeight = 1.5.em
        width = 100.pct
        minWidth = 10.em
        border = "none"
        textAlign = TextAlign.center
        color = Color.black
        borderBottomStyle = BorderStyle.solid
        borderBottomWidth = 2.px
    }

    private val tagName by css {
        transition(duration = .2.s)
        height = 1.5.em
        width = 100.pct
        lineHeight = LineHeight(height.value)
        textAlign = TextAlign.left
        display = Display.flex
        alignItems = Align.center
        justifyContent = JustifyContent.start
    }

    val labelUnFocused by css {
        +tagName
        position = Position.absolute
        fontSize = 0.8.em
        bottom = 0.pct
    }

    val labelFocused by css {
        +tagName
        position = Position.absolute
        fontSize = 0.8.em
        bottom = 100.pct
    }
}