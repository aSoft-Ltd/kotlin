package tz.co.asoft

import kotlinx.html.DIV
import react.RBuilder
import styled.StyledDOMBuilder

inline fun <T> RBuilder.ListAdapter(
    data: List<T>,
    cols: String = "1fr",
    gap: String = "1em",
    crossinline builder: StyledDOMBuilder<DIV>.(T) -> Unit
) = Grid(cols, gap) {
    data.forEach { builder(it) }
}