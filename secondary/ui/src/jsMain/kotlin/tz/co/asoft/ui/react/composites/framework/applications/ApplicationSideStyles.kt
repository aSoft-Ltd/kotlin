package tz.co.asoft.ui.react.composites.framework.applications

import kotlinx.css.*
import styled.StyleSheet
import tz.co.asoft.ui.react.tools.onDesktop
import tz.co.asoft.ui.react.tools.onMobile

object ApplicationSideStyles : StyleSheet("application-side") {
    val userImage by css {
        borderRadius = 50.pct
        cursor = Cursor.pointer
        onMobile {
            height = 2.8.em
            width = 2.8.em
        }
        onDesktop {
            height = 1.5.em
            width = 1.5.em
        }
    }
}