package tz.co.asoft.geo

val Collection<Cord>.average: Cord
    get() = Cord().apply {
        lat = 0.0
        lng = 0.0
        forEach {
            lat += it.lat
            lng += it.lng
        }
        lat /= size
        lng /= size
    }

val Array<out Cord>.average: Cord get() = toList().average

fun Cord.isGMT() = (lat == 0.0 && lng == 0.0)