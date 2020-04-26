package tz.co.asoft.ui.react.composites.reacteventtimeline

import react.RBuilder
import react.RHandler

fun RBuilder.timeline(handler: RHandler<TimelineProps>) = child(Timeline::class, handler)

fun RBuilder.timelineEvent(handler: RHandler<TimelineEventProps> = {}) = child(TimelineEvent::class) {
    attrs.container = "card"
    handler()
}