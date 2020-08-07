package tz.co.asoft

import kotlinext.js.jsObject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.html.id
import org.w3c.dom.HTMLAudioElement
import react.RBuilder
import react.RProps
import react.child
import react.functionalComponent
import styled.styledAudio
import styled.styledDiv
import kotlin.math.round

private external interface AudioProps : RProps {
    var src: String
    var volume: Double
}

private external interface ScrollerProps : RProps {
    var updater: StateFlow<Double>
}

private val Scroller = functionalComponent<ScrollerProps> { props ->
    val progress = props.updater.asState()
    styledDiv { +"${round(progress * 100)}%" }
}

private val Audio = functionalComponent<AudioProps> { props ->
    val id = useUIID("audio-player")
    val progress = MutableStateFlow(0.0)
    val scope = useScope()

    styledAudio {
        attrs.src = props.src
        attrs.controls = true
        attrs.id = id.value
    }

    fun HTMLAudioElement.updateProgress() {
        progress.value = currentTime / duration
        if (!paused) {
            scope.launch {
                delay(500)
                updateProgress()
            }
        }
    }

    scope.launch {
        id.get<HTMLAudioElement>().apply {
            volume = props.volume
            onplaying = { updateProgress() }
            onpause = { updateProgress() }
            onseeked = { updateProgress() }
            onseeking = { updateProgress() }
        }
    }
}

/**
 * @param volume - [0..1], default: 0.5
 */
fun RBuilder.Audio(src: String, volume: Double = 0.5) = child(Audio, jsObject<AudioProps>().also {
    it.src = src
    it.volume = volume
})