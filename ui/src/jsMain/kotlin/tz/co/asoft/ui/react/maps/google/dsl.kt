package tz.co.asoft.ui.react.maps.google

import kotlinext.js.js
import kotlinext.js.jsObject
import react.RBuilder
import react.RHandler

private fun RBuilder.loadScript(handler: RHandler<LoadScript.Props> = {}) = child(LoadScript::class) {
    attrs {
        id = "google-maps-script"
        googleMapsApiKey = "AIzaSyCqJVvryPjgC6uCLVxwo8CnZaBdprplOTo"
    }
    handler()
}

private fun RBuilder.googleMap(handler: RHandler<GoogleMap.Props> = {}) = child(GoogleMap::class) {
    attrs {
        center = jsObject { lat = 0.0; lng = 0.0 }
        zoom = 13
        mapContainerStyle = js {
            height = "90%"
            width = "100%"
        }
    }
    handler()
}

fun RBuilder.polyLine(paths: Array<LatLng>, handler: RHandler<Polyline.Props> = {}) = child(Polyline::class) {
    attrs {
        options = jsObject {
            strokeColor = "#FF0000"
            strokeOpacity = 0.8
            strokeWeight = 2
            fillColor = "#FF0000"
            fillOpacity = 0.35
            clickable = false
            draggable = false
            editable = false
            visible = true
            radius = 30000
            zIndex = 1
        }
        options.paths = paths
    }
    attrs.path = paths
    handler()
}

fun RBuilder.marker(handler: RHandler<Marker.Props> = {}) = child(Marker::class) {
    attrs { position = jsObject { lat = 0.0; lng = 0.0 } }
    handler()
}

fun RBuilder.infoWindow(handler: RHandler<InfoWindow.Props> = {}) = child(InfoWindow::class) {
    attrs { position = jsObject { lat = 0.0; lng = 0.0 } }
    handler()
}

fun RBuilder.map(handler: RHandler<GoogleMap.Props> = {}) = loadScript {
    googleMap {
        handler()
    }
}

fun Array<Double>.toLatLng() = jsObject<LatLng> {
    lat = get(0)
    lng = get(1)
}