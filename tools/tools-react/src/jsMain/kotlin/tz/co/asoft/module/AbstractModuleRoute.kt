package tz.co.asoft.module

import react.RBuilder
import react.RProps
import react.ReactElement
import react.buildElement
import react.router.dom.RouteResultProps

abstract class AbstractModuleRoute<T : RProps>(
    val permits: List<String>,
    val path: String,
    val show: suspend () -> Boolean,
    val render: (props: RouteResultProps<out T>) -> ReactElement?
)

fun <T : RProps> ModuleRoute(
    path: String,
    permits: List<String>,
    show: suspend () -> Boolean = { true },
    builder: RBuilder.(props: RouteResultProps<out T>) -> Unit
): AbstractModuleRoute<T> {
    val render: (RouteResultProps<out T>) -> ReactElement? = {
        buildElement { builder(it) }
    }
    return object : AbstractModuleRoute<T>(
        permits = permits,
        path = path,
        show = show,
        render = render
    ) {}
}