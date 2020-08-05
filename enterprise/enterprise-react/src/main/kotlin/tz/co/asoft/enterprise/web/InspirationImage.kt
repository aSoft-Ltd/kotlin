package tz.co.asoft.enterprise.web

import kotlinx.css.*
import react.RBuilder
import styled.css
import styled.styledDiv
import tz.co.asoft.onDesktop
import tz.co.asoft.onMobile

fun RBuilder.InspirationImage(url: String) = styledDiv {
    css {
        width = 100.vw
        position = Position.relative
    }
    styledDiv {
        css {
            width = 100.vw
            background = "url('$url')"
            backgroundPosition = "center center"
            backgroundRepeat = BackgroundRepeat.noRepeat
            backgroundSize = "cover"
            onMobile { height = 100.vh }
            onDesktop { height = 100.vh }
        }
    }
}