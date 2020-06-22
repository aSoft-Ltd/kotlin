package tz.co.asoft.ui.react.widget.hamburger

import kotlinx.css.*
import kotlinx.css.properties.Timing
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import styled.StyleSheet

object HamburgerStyles : StyleSheet("hamburger-styles") {
    val wrapper by css {
        textAlign = TextAlign.center
        position = Position.relative
        borderRadius = 50.pct
        display = Display.inlineBlock
        cursor = Cursor.pointer
    }

    val line by css {
        position = Position.absolute
        borderTop = "solid 1px white"
        borderBottom = "solid 1px white"
        backgroundColor = Color.white
        width = 60.pct
        borderRadius = 5.px
        transition(duration = 0.2.s, timing = Timing("cubic-bezier(.7,.08,.87,1.28)"))
        left = 20.pct
    }

    val topLine by css {

    }
}