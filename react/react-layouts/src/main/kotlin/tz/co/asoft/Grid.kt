package tz.co.asoft

import kotlinx.css.*
import kotlinx.html.DIV
import react.RBuilder
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv

fun RBuilder.Grid(
    cols: String = "1fr",
    rows: String = "1fr",
    gap: String,
    builder: StyledDOMBuilder<DIV>.(Theme) -> Unit
) = ThemeConsumer { theme ->
    styledDiv {
        css {
            display = Display.grid
            gridTemplateColumns = GridTemplateColumns(cols)
            gridTemplateRows = GridTemplateRows(rows)
            width = 100.pct
            this.gap = Gap(gap)
        }
        builder(theme)
    }
}

fun RBuilder.Grid(
    cols: String = "1fr",
    rows: String = "1fr",
    gap: LinearDimension = 1.em,
    builder: StyledDOMBuilder<DIV>.(Theme) -> Unit
) = Grid(cols, rows, gap.value, builder)