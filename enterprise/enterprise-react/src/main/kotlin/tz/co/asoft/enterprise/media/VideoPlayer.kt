package tz.co.asoft.enterprise.media

import tz.co.asoft.enterprise.media.VideoPlayer.Props
import kotlinx.css.em
import kotlinx.css.minHeight
import kotlinx.css.pct
import kotlinx.css.width
import react.*
import styled.css
import styled.styledSource
import styled.styledVideo

private class VideoPlayer(p: Props) : RComponent<Props, RState>(p) {
    class Props(var src: String, var controls: Boolean) : RProps

    override fun RBuilder.render(): dynamic = styledVideo {
        attrs { controls = props.controls }
        css {
            width = 100.pct
            minHeight = 10.em
        }

        styledSource {
            attrs.src = props.src
        }
    }
}

fun RBuilder.VideoPlayer(src: String, controls: Boolean = true) = child(VideoPlayer::class.js, Props(src, controls)) {}