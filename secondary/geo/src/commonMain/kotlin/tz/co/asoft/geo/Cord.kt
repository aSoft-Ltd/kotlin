package tz.co.asoft.geo

import tz.co.asoft.klock.DateTime
import kotlinx.serialization.Serializable

@Serializable
open class Cord(open var lat: Double = 0.0, open var lng: Double = 0.0, open var alt: Double = 0.0) {

    open var timestamp = DateTime.nowUnixLong()
    
    open fun toArray() = arrayOf(lat, lng)

    override fun toString(): String = "Cord {lat: $lat, lng: $lng}"

    companion object {
        val fake
            get() = Cord().apply {
                lat = (6740018..6873602).random().toDouble() / -1_000_000.0
                lng = (39168602..39348200).random().toDouble() / 1_000_000.0
            }
    }
}