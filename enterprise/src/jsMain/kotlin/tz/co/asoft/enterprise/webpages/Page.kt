package tz.co.asoft.enterprise.webpages

import react.RBuilder
import react.RProps
import react.ReactElement
import react.buildElement
import react.router.dom.RouteResultProps

abstract class Page<T : RProps>(
    val route: String,
    val render: (props: RouteResultProps<out T>) -> ReactElement?
)

fun <T : RProps> WebPage(route: String, builder: RBuilder.(RouteResultProps<out T>) -> Unit): Page<T> {
    val render: (RouteResultProps<out T>) -> ReactElement? = {
        buildElement { builder(it) }
    }
    return object : Page<T>(route, render) {}
}