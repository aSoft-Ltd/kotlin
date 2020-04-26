@file:JsModule("react-event-timeline")
@file:JsNonModule

package tz.co.asoft.ui.react.composites.reacteventtimeline

import react.Component
import react.RProps
import react.RState
import react.ReactElement

external interface TimelineProps : RProps {
    var className: String
    var style: dynamic

    /**
     * accepts left|right, default: left
     */
    var orientation: String

    /**
     * the hexacode of the color
     */
    var lineColor: String
}


external interface TimelineEventProps : RProps {
    /**
     * The title of the event. Can be string or any DOM element node(s)
     */
    var title: dynamic

    /**
     * The time at which the event occurred. Can be datetime string or any DOM element node(s)
     */
    var createdAt: dynamic

    /**
     * If you prefer having the title at the top and some caption below, omit createdAt and specify title and subtitle
     */
    var subtitle: dynamic

    /**
     * The icon to show as event lable. Can be a SVG or font icon
     */
    var icon: dynamic

    /**
     *  	The icon to show as event lable. Can be a SVG or font icon
     */
    var iconStyle: dynamic

    var contentStyle: dynamic

    /**
     * Optional value card will render event as a Card
     */
    var container: String

    /**
     * Custom CSS styling for the bubble containing the icon
     */
    var bubbleStyle: dynamic

    /**
     * Override style for the entire event container.
     * Can be used to modify card appearance if container is selected as card
     */
    var style: dynamic

    /**
     * Override style for the card header if container is card
     */
    var cardHeaderStyle: dynamic
}

@JsName("Timeline")
external class Timeline : Component<TimelineProps, RState> {
    override fun render(): ReactElement?
}

@JsName("TimelineEvent")
external class TimelineEvent : Component<TimelineEventProps, RState> {
    override fun render(): ReactElement?
}


