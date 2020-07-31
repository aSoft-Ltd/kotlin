package tz.co.asoft

import react.RBuilder
import react.RProps
import react.ReactElement
import react.buildElement
import react.router.dom.RouteResultProps

open class WebPage<T : RProps>(
    val route: String,
    builder: RBuilder.(RouteResultProps<out T>) -> ReactElement?
) {
    val render: (RouteResultProps<out T>) -> ReactElement? = {
        buildElement { builder(it) }
    }
}