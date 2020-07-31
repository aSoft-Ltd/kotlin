package tz.co.asoft.enterprise.web

import kotlinx.css.*
import react.RBuilder
import styled.css
import styled.styledDiv
import tz.co.asoft.enterprise.layout.AspectRationDiv
import tz.co.asoft.enterprise.layout.Grid
import tz.co.asoft.*

class Intro(
    val heading: String,
    val body: String,
    val image:String
)

fun RBuilder.Introduction(intro: Intro) = Grid { theme ->
    css {
        onDesktop {
            gridTemplateColumns = GridTemplateColumns("2fr 1fr")
            gap = Gap("5em")
            padding(horizontal = 20.pct)
        }

        onMobile {
            gridTemplateColumns = GridTemplateColumns("1fr")
            gap = Gap("1em")
            padding(horizontal = 0.5.em)
        }
    }
    Grid(rows = "auto auto", gap = "1em") {
        css {
            alignSelf = Align.center
        }
        styledDiv {
            css {
                justifySelf = JustifyContent.center
                +theme.text.h2.clazz
            }
            +intro.heading
        }

        styledDiv {
            css {
                +theme.text.body1.clazz
                textAlign = TextAlign.justify
            }
            +intro.body
        }
    }

    AspectRationDiv { Image(src = intro.image, radius = 10.px) }
}