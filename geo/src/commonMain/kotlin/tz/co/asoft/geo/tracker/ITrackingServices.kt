package tz.co.asoft.geo.tracker

import tz.co.asoft.geo.Track

interface ITrackingServices {
    var update_interval : Long
    var update_distance : Float
    fun setTrackListener(handler: (Track?) -> Unit)
    fun getLocation() : Track?
    fun dispatch()
    fun stop()
}