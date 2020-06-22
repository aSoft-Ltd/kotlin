package tz.co.asoft.ui.react.media

import tz.co.asoft.ui.react.media.VideoPlayer.Props
import tz.co.asoft.ui.react.tools.ThemedProps
import kotlinx.css.em
import kotlinx.css.minHeight
import kotlinx.css.pct
import kotlinx.css.width
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.css
import styled.styledSource
import styled.styledVideo

class VideoPlayer(p: Props) : RComponent<Props, RState>(p) {
    object Props : ThemedProps() {
        var src = ""
        var controls = true

        init {
            css = {
                width = 100.pct
                minHeight = 10.em
            }
        }
    }

    override fun RBuilder.render(): dynamic = styledVideo {
        attrs { controls = props.controls }
        css {
            +props.css
        }

        styledSource {
            attrs.src = props.src
        }
    }
}

fun RBuilder.videoPlayer(src: String = "", handler: RHandler<Props> = {}) = child(VideoPlayer::class.js, Props) {
    attrs.src = src
    handler()
}