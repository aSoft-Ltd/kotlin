package tz.co.asoft.ui.react.graphs.echarts

import kotlinext.js.js
import react.RBuilder
import react.RHandler

fun RBuilder.chart(handler: RHandler<ReactEcharts.Props>) = child(ReactEcharts::class) {
    attrs.style = js {
        height = "100%"
        width = "100%"
    }
    handler()
}