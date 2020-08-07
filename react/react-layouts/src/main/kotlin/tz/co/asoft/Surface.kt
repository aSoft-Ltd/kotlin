package tz.co.asoft

import kotlinx.css.*
import kotlinx.css.Image
import kotlinx.html.DIV
import react.RBuilder
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv
import kotlinx.css.Color as CssColor

sealed class Surface {
    class Image(val url: String, val overlayColor: CssColor? = null, val color: CssColor? = null) :Surface()
    class Color(val bg: CssColor, val overlayColor: CssColor? = null, val color: CssColor? = null) : Surface()
}

private fun RBuilder.Surface(
    bgColor: CssColor?,
    color: CssColor?,
    bgImageUrl: String?,
    overlayColor: CssColor?,
    margin: LinearDimension,
    builder: StyledDOMBuilder<DIV>.(Theme) -> Unit
) = ThemeConsumer { theme ->
    styledDiv {
        css {
            width = 100.pct - margin * 2
            margin(margin)
            this.color = color ?: theme.onSurfaceColor
            if (bgImageUrl == null) {
                backgroundColor = bgColor ?: theme.surfaceColor
            } else {
                backgroundImage = Image(value = "url('$bgImageUrl')")
                backgroundRepeat = BackgroundRepeat.noRepeat
                backgroundSize = "cover"
            }
        }
        styledDiv {
            css {
                padding(0.5.em)
                width = 100.pct
                children { maxWidth = 100.pct }
                overlayColor?.let { backgroundColor = it }
            }
            builder(theme)
        }
    }
}

fun RBuilder.Surface(
    s: Surface,
    margin: LinearDimension = 0.em,
    builder: StyledDOMBuilder<DIV>.(Theme) -> Unit
) = when (s) {
    is Surface.Image -> Surface(
        bgImageUrl = s.url,
        color = s.color,
        overlayColor = s.overlayColor,
        margin = margin,
        builder = builder
    )
    is Surface.Color -> Surface(
        bgColor = s.bg,
        color = s.color,
        overlayColor = s.overlayColor,
        margin = margin,
        builder = builder
    )
}

fun RBuilder.Surface(
    bgColor: CssColor? = null,
    color: CssColor? = null,
    overlayColor: CssColor? = null,
    margin: LinearDimension = 0.em,
    builder: StyledDOMBuilder<DIV>.(Theme) -> Unit
) = Surface(bgColor, color, null, overlayColor, margin, builder)

fun RBuilder.Surface(
    bgImageUrl: String,
    color: CssColor? = null,
    overlayColor: CssColor? = null,
    margin: LinearDimension = 0.em,
    builder: StyledDOMBuilder<DIV>.(Theme) -> Unit
) = Surface(null, color, bgImageUrl, overlayColor, margin, builder)