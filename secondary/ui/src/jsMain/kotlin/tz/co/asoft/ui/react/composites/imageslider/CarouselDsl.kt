package tz.co.asoft.ui.react.composites.imageslider

import kotlinext.js.require
import react.RBuilder
import react.RHandler

private var cssLoaded = false

fun RBuilder.carousel(handler: RHandler<CarouselProps>) = child(Carousel::class) {
    if (!cssLoaded) {
        cssLoaded = true
        require("react-responsive-carousel/lib/styles/carousel.min.css")
    }
    attrs {
        showThumbs = false
        stopOnHover = false
        autoPlay = true
        interval = 5000
        infiniteLoop = true
    }
    handler()
}
