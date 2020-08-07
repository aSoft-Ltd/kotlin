package tz.co.asoft

import kotlinx.css.*
import styled.StyleSheet

internal object styles : StyleSheet("widget-styles") {
    val wrapper by css {
        height = 100.vh
        width = 100.pct
        display = Display.flex
        justifyContent = JustifyContent.center
        alignItems = Align.center
        flexDirection = FlexDirection.column
    }
}