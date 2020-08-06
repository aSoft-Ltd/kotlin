package tz.co.asoft

import kotlinx.css.*
import react.RBuilder
import styled.css
import styled.styledImg

fun RBuilder.Image(
    src: String,
    alt: String = src,
    width: LinearDimension = 100.pct,
    maxWidth: LinearDimension = 100.pct,
    height: LinearDimension = LinearDimension.auto,
    radius: LinearDimension = 0.px
) = styledImg(src = src, alt = alt) {
    css {
        this.width = width
        this.height = height
        this.borderRadius = radius
        this.maxWidth = maxWidth
    }
}