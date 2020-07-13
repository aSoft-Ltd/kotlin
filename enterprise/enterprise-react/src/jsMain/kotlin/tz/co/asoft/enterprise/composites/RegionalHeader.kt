package tz.co.asoft.enterprise.composites

import kotlinx.css.*
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLSelectElement
import org.w3c.dom.events.Event
import react.RBuilder
import react.dom.key
import styled.*
import tz.co.asoft.react.icons.FaUserAlt
import tz.co.asoft.react.icons.MdClose
import tz.co.asoft.theme.ThemeConsumer
import tz.co.asoft.theme.dropdown_clazz
import tz.co.asoft.theme.onPrimaryColor
import tz.co.asoft.tools.justifySelf
import tz.co.asoft.tools.onDesktop
import tz.co.asoft.tools.onMobile

fun RBuilder.RegionalHeader(
    photoUrl: String,
    title: String,
    selectedRegion: String?,
    regions: List<String>,
    onRegionChanged: (String) -> Unit,
    onCloseClicked: () -> Unit
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
            if (photoUrl.isNotBlank()) {
                styledImg(src = photoUrl) {
                    css {
                        width = 100.pct
                        borderRadius = 50.pct
                    }
                }
            } else {
                styledDiv {
                    css { fontSize = 2.em }
                    FaUserAlt {}
                }
            }
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

        styledDiv {
            css {
                onDesktop {
                    display = Display.none
                }
                onMobile { fontSize = 1.5.em }
            }
            attrs.onClickFunction = { onCloseClicked() }
            MdClose {}
        }
    }
}