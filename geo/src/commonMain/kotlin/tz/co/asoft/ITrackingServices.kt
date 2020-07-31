package tz.co.asoft

interface ITrackingServices {
    var update_interval: Long
    var update_distance: Float
    fun onCordChanged(handler: (Cord?) -> Unit)
    fun getLocation(): Cord?
    fun dispatch()
    fun stop()
}