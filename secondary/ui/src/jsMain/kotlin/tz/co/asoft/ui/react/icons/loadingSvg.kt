package tz.co.asoft.ui.react.icons

import kotlinext.js.js
import kotlinx.css.CSSBuilder
import react.RBuilder
import react.createElement
import styled.css
import styled.styledDiv

fun RBuilder.loadingSvg(cssBuilder: CSSBuilder.() -> Unit = {}) = styledDiv {
    css(cssBuilder)
    child(createElement("svg", js {
        version = 1.0
        width = "64px"
        height = "64px"
        viewBox = "0 0 128 128"
        xmlSpace = "preserve"
    }, createElement("rect", js {
        x = 0
        y = 0
        width = "100%"
        height = "100%"
        fill = "#FAFAFA00"
    }), createElement("g", js { }, createElement("circle", js {
        cx = 16
        cy = 64
        r = 16
        fill = "currentColor"
        fillOpacity = 1
    }), createElement("circle", js {
        cx = 16
        cy = 64
        r = "14.344"
        fill = "currentColor"
        fillOpacity = 1
        transform = "rotate(45 64 64)"
    }), createElement("circle", js {
        cx = 16
        cy = 64
        r = "12.531"
        fill = "currentColor"
        fillOpacity = 1
        transform = "rotate(90 64 64)"
    }), createElement("circle", js {
        cx = 16
        cy = 64
        r = "10.75"
        fill = "currentColor"
        fillOpacity = 1
        transform = "rotate(135 64 64)"
    }), createElement("circle", js {
        cx = 16
        cy = 64
        r = "10.063"
        fill = "currentColor"
        fillOpacity = 1
        transform = "rotate(180 64 64)"
    }), createElement("circle", js {
        cx = 16
        cy = 64
        r = "8.063"
        fill = "currentColor"
        fillOpacity = 1
        transform = "rotate(225 64 64)"
    }), createElement("circle", js {
        cx = 16
        cy = 64
        r = "6.438"
        fill = "currentColor"
        fillOpacity = 1
        transform = "rotate(270 64 64)"
    }), createElement("circle", js {
        cx = 16
        cy = 64
        r = "5.375"
        fill = "currentColor"
        fillOpacity = 1
        transform = "rotate(315 64 64)"
    }), createElement("animateTransform", js {
        attributeName = "transform"
        this.type = "rotate"
        values = "0 64 64;315 64 64;270 64 64;225 64 64;180 64 64;135 64 64;90 64 64;45 64 64"
        calcMode = "discrete"
        dur = "720ms"
        repeatCount = "indefinite"
    }))))
}