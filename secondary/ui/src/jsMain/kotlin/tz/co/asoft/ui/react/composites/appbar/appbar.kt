package tz.co.asoft.ui.react.composites.appbar

import tz.co.asoft.ui.theme.main
import tz.co.asoft.ui.react.layout.horizontalLayout
import tz.co.asoft.ui.react.tools.ThemedProps
import tz.co.asoft.ui.react.tools.css
import kotlinx.css.*
import kotlinx.css.properties.boxShadow
import react.*

class AppBar(p: Props) : RComponent<AppBar.Props, AppBar.State>(p) {
    object Props : ThemedProps()
    class State : RState

    override fun RBuilder.render(): dynamic = horizontalLayout {
        props.children()
        css {
            minHeight = 3.em
            boxShadow(Color.gray, offsetY = 4.px, blurRadius = 3.px, spreadRadius = 1.px)
            position = Position.sticky
            backgroundColor = props.theme.primaryColor.main()
            color = props.theme.text.onPrimary.main()
            top = 0.px
            left = 0.px
            maxHeight = 5.vh
            +props.css
        }
    }
}

fun RBuilder.appBar(handler: RHandler<AppBar.Props>) = child(AppBar::class.js, AppBar.Props, handler)