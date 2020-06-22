@file:JsModule("react-responsive-carousel")
@file:JsNonModule

package tz.co.asoft.ui.react.composites.imageslider

import react.Component
import react.RProps
import react.RState
import react.ReactElement

external interface CarouselProps : RProps {
    var showThumbs: Boolean
    var showStatus: Boolean
    var showIndicators: Boolean

    /**
    In px
     */
    var thumbWidth: Int
    var infiniteLoop: Boolean
    var selectedItem: Int

    /**
     *  acceptable values: horizontal|vertical
     */
    var axis: String
    var onChange: () -> Unit
    var onClickItem: () -> Unit
    var onClickThumb: () -> Unit
    var width: String
    var useKeyboardArrows: Boolean
    var autoPlay: Boolean
    var stopOnHover: Boolean
    var className: String

    /**
     * in ms, default is 5000
     */
    var interval: Int

    /**
     * in ms, default is 350
     */
    var transitionTime: Int
}

@JsName("Carousel")
external class Carousel : Component<CarouselProps, RState> {
    override fun render(): ReactElement?
}