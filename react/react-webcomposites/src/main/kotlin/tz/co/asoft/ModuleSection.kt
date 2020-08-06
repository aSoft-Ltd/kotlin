package tz.co.asoft

import kotlinx.css.*
import react.RBuilder
import styled.css
import styled.styledDiv

class ModuleSection(
    val heading: String,
    val modules: List<String>
)

fun RBuilder.ModuleSection(ms: ModuleSection) = Grid { theme ->
    styledDiv {
        css {
            +theme.text.h2.clazz
            textAlign = TextAlign.center
        }
        +ms.heading
    }

    Grid {
        css {
            onDesktop {
                padding(horizontal = 20.pct)
                gridTemplateColumns = GridTemplateColumns("1fr 1fr")
            }
            onMobile {
                padding(0.5.em)
            }
        }
        ms.modules.forEachIndexed { i, it -> styledDiv { +"${i + 1}. $it" } }
    }
}