package tz.co.asoft.ui.react.widget.checkbox

import kotlinx.css.*
import kotlinx.css.properties.*
import styled.StyleSheet

object CheckBoxStyles : StyleSheet("asoft-view-check-box") {
    val root by css {
        display = Display.inlineBlock
        verticalAlign = VerticalAlign.middle
        position = Position.relative
        cursor = Cursor.pointer
    }

    val tail by css {
        transition(duration = 0.5.s)
        position = Position.absolute
    }

    val tailLeftChecked by css {
        +tail
        left = 10.pct
        bottom = 25.pct
        height = 40.pct
        width = 25.pct
        put("transform-origin", "100% 100%")
        transform {
            rotateZ((-405).deg)
        }
    }

    val tailLeftUnChecked by css {
        +tail
        opacity = 0
        bottom = 10.pct
        height = 80.pct
        width = 50.pct
        put("transform-origin", "100% 50%")
        transform {
            rotateZ((45).deg)
        }
    }

    val tailRightChecked by css {
        +tail
        bottom = 25.pct
        right = (-10).pct
        width = 75.pct
        height = 80.pct
        put("transform-origin", "0% 100%")
        transform {
            rotateZ(45.deg)
        }
    }

    val tailRightUnChecked by css {
        opacity = 0
        bottom = 10.pct
        width = 50.pct
        height = 75.pct
        put("transform-origin", "0% 50%")
        transform {
            rotateZ((315).deg)
        }
    }
}