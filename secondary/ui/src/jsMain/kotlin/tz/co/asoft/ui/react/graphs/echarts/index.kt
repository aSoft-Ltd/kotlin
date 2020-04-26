@file:JsModule("echarts-for-react")
@file:JsNonModule

// depends on "echarts"
package tz.co.asoft.ui.react.graphs.echarts

import tz.co.asoft.ui.react.graphs.echarts.ReactEcharts.Props
import react.Component
import react.RProps
import react.RState

@JsName("default")
external class ReactEcharts : Component<Props, RState> {
    interface Props : RProps {
        var style: dynamic
        var option: Option
        var notMerge: Boolean?
        var lazyUpdate: Boolean?
        var className: String?
        var theme: String?
        var onChartReady: ((args: Any) -> Any)?
        var showLoading: Boolean?
        var loadingOption: dynamic
        var onEvents: dynamic
        var echarts: Any?
        var opts: optsMap?
        var shouldSetOption: ((args: Any) -> Any)?
    }

    override fun render(): dynamic
}

external interface optsMap {
    var devicePixelRatio: Number? get() = definedExternally; set(value) = definedExternally
    var renderer: dynamic /* String /* "canvas" */ | String /* "svg" */ */ get() = definedExternally; set(value) = definedExternally
    var width: dynamic /* Number | String /* "auto" */ | Nothing? */ get() = definedExternally; set(value) = definedExternally
    var height: dynamic /* Number | String /* "auto" */ | Nothing? */ get() = definedExternally; set(value) = definedExternally
}