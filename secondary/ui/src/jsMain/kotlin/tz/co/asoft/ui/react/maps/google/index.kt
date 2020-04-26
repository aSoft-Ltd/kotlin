@file:JsModule("@react-google-maps/api")
@file:JsNonModule

package tz.co.asoft.ui.react.maps.google

import react.Component
import react.RProps
import react.RState

external interface LatLng {
    var lat: Double
    var lng: Double
}

external class GoogleMap : Component<GoogleMap.Props, RState> {
    interface Props : RProps {
        var id: String
        var center: LatLng
        var zoom: Int
        var mapContainerStyle: dynamic
    }

    override fun render(): dynamic
}

external class Marker : Component<Marker.Props, RState> {
    interface Props : RProps {
        var position: LatLng
    }

    override fun render(): dynamic
}

external class InfoWindow : Component<InfoWindow.Props, RState> {
    interface Props : RProps {
        var position: LatLng
    }

    override fun render(): dynamic
}

external class LoadScript : Component<LoadScript.Props, RState> {
    interface Props : RProps {
        var id: String
        var googleMapsApiKey: String
    }

    override fun render(): dynamic
}

external class Polyline : Component<Polyline.Props, RState> {
    interface Props : RProps {
        var path: Array<LatLng>
        var options: Options
        var onLoad: (polyline: dynamic) -> Unit
    }

    interface Options {
        var strokeColor: String
        var strokeOpacity: Double
        var strokeWeight: Int
        var fillColor: String
        var fillOpacity: Double
        var clickable: Boolean
        var draggable: Boolean
        var editable: Boolean
        var visible: Boolean
        var radius: Int
        var paths: Array<LatLng>
        var zIndex: Int
    }

    override fun render(): dynamic
}