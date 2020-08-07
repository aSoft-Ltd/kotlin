package tz.co.asoft

import kotlinx.html.DIV
import react.RBuilder
import styled.StyledDOMBuilder

fun <T> RBuilder.GridAdapter(
    data: List<T>,
    cols: String = "1fr",
    rows: String = "1fr",
    gap: String = "1em",
    builder: StyledDOMBuilder<DIV>.(T) -> Unit
) = Grid(cols, rows, gap) {
    data.forEach { builder(it) }
}