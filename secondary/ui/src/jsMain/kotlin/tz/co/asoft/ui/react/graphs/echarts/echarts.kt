package tz.co.asoft.ui.react.graphs.echarts

import org.w3c.dom.Element

@JsModule("echarts")
external object echarts {
    fun init(dom: Element, theme: dynamic = definedExternally, ops: dynamic = definedExternally): EChart
}

external interface EChart {
    fun setOption(option: Option)
}

external interface Option {
    var title: Title
    var tooltip: Tooltip
    var toolbox: Toolbox
    var legend: Legend
    var xAxis: Array<XAxis>
    var yAxis: Array<YAxis>
    var series: Array<Series>
    var color: Array<String>
    var calculable: Boolean
}

external interface Title : Alignment{
    var text: String
    var subtext: String
}

external interface Tooltip {
    var trigger: String
    var formatter: String
}

external interface Toolbox


external interface Legend: Alignment {
    var data: Array<String>
    var orient: String
}

external interface Axis {
    var type: String
    var data: Array<String>
    var axisTick: AXisTick
}

external interface AXisTick : Showable

external interface XAxis : Axis

external interface YAxis : Axis

external interface Series {
    var name: String
    var type: String
    var radius: Array<String>
    var data: Array<dynamic>
    var label: Label
    var roseType: String
    var labelLine: Label
    var avoidLabelOverlap: Boolean
    var center: Array<String>
}

external interface PieData {
    var name: String
    var value: dynamic
}

external interface Label {
    var normal: Normal
    var emphasis: Emphasis
}

external interface Alignment {
    var x: String
    var y: String
}


external interface Normal : Showable

external interface Emphasis : Showable {
    var textStyle : dynamic
}

external interface Showable {
    var position: String
    var show: Boolean
}