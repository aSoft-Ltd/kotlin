package tz.co.asoft.enterprise.web

import kotlinx.css.*
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import react.RBuilder
import styled.css
import styled.styledDiv
import tz.co.asoft.enterprise.layout.Grid
import tz.co.asoft.*
import tz.co.asoft.enterprise.action.AButton
import tz.co.asoft.enterprise.action.ActionButton

class PictureDetailGrid(
    val heading: String,
    val content: List<PicDetailInfo>,
    val contentPerRow: Int = 3
)

fun RBuilder.PictureDetailGrid(cs: PictureDetailGrid, spacing: LinearDimension = 3.em) = Grid(gap = spacing.value) { theme ->
    Grid {
        css {
            +theme.text.h2.clazz
            textAlign = TextAlign.center
        }
        +cs.heading
    }
    cs.content.chunked(cs.contentPerRow).forEach { rowCircles ->
        Grid {
            css {
                onDesktop {
                    gridTemplateColumns = GridTemplateColumns(rowCircles.joinToString(" ") { "1fr" })
                    gap = Gap(spacing.value)
                    padding(horizontal = spacing)
                }
                onMobile {
                    padding(horizontal = 10.pct)
                    gridTemplateColumns = GridTemplateColumns("1fr")
                }
            }
            for (c in rowCircles) PicDetailGridCell(c)
        }
    }
}

class PicDetailInfo(
    val title: String,
    val image: RBuilder.() -> Unit,
    val body: String,
    val action: AButton<Any>? = null
)

fun RBuilder.PicDetailGridCell(ci: PicDetailInfo) = Grid { theme ->
    css {
        position = Position.relative
        transition(duration = 0.75.s)
        cursor = Cursor.pointer
    }

    styledDiv {
        css { textAlign = TextAlign.center }
        ci.image(this)
    }

    styledDiv {
        css {
            +theme.text.h3.clazz
            textAlign = TextAlign.center
        }
        +ci.title
    }

    styledDiv {
        css {
            justifySelf = JustifyContent.center
            textAlign = TextAlign.center
        }
        +ci.body
    }

    if (ci.action != null) styledDiv {
        css {
            justifySelf = JustifyContent.center
            textAlign = TextAlign.center
        }
        ActionButton(ci.action, Any())
    }
}