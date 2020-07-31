package tz.co.asoft.enterprise.web

import kotlinx.css.*
import react.RBuilder
import styled.css
import styled.styledDiv
import tz.co.asoft.clazz
import tz.co.asoft.enterprise.layout.Grid
import tz.co.asoft.enterprise.layout.Surface
import tz.co.asoft.justifySelf
import tz.co.asoft.onDesktop
import tz.co.asoft.onMobile

fun RBuilder.HowItWorks(
    vararg stages: String
) = Surface(bgImageUrl = "/bg_blur.jpg", color = Color.white) { theme ->
    css {
        padding(vertical = 3.em)
    }
    Grid {
        css {
            +theme.text.h1.clazz
            textAlign = TextAlign.center
        }
        +"How It Works"
    }
    Grid {
        css {
            paddingTop = 3.em
            children {
                +theme.text.h2.clazz
                justifySelf = JustifyContent.center
            }

            onDesktop {
                gridTemplateColumns = GridTemplateColumns("1fr 1fr 1fr")
            }

            onMobile {
                gridTemplateColumns = GridTemplateColumns("1fr")
            }
        }
        stages.forEachIndexed { index, s -> styledDiv { +"${index + 1}. $s" } }
    }
}