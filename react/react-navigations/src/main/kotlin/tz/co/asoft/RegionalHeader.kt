package tz.co.asoft

import kotlinx.css.*
import kotlinx.css.Color
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLSelectElement
import org.w3c.dom.events.Event
import react.RBuilder
import react.dom.key
import styled.*

fun RBuilder.RegionalHeader(
    photoUrl: String,
    title: String,
    selectedRegion: String?,
    regions: List<String>,
    onRegionChanged: (String) -> Unit
) = ThemeConsumer { theme ->
    styledDiv {
        css {
            height = 6.em
            gridTemplateColumns = GridTemplateColumns("2fr 4fr 1fr")
            position = Position.relative
            display = Display.grid
            children {
                alignSelf = Align.center
                justifySelf = JustifyContent.center
            }
        }

        styledDiv {
            css {
                width = 3.5.em
                height = 3.5.em
                border = "solid 1px ${theme.color.onPrimary}"
                borderRadius = 50.pct
                display = Display.grid
                children {
                    alignSelf = Align.center
                    justifySelf = JustifyContent.center
                }
            }
            ProfilePic(
                name = title,
                src = photoUrl,
                radius = 50.pct
            )
        }

        styledDiv {
            css {
                display = Display.grid
                gridTemplateColumns = GridTemplateColumns("1fr")
                alignItems = Align.center
            }

            styledDiv {
                css { fontSize = 1.5.em }
                +title
            }

            styledSelect {
                css {
                    border = "none"
                    backgroundColor = Color.transparent
                    color = theme.onPrimaryColor
                    +theme.dropdown_clazz
                }
                attrs.onChangeFunction = { e: Event ->
                    e.asDynamic().persist()
                    onRegionChanged(e.target.unsafeCast<HTMLSelectElement>().value)
                }
                selectedRegion?.let { attrs["defaultValue"] = it }
                regions.forEach {
                    styledOption {
                        attrs.key = it
                        +it
                    }
                }
            }
        }
    }
}