package tz.co.asoft.places

import kotlinx.serialization.Serializable

@Serializable
data class Cord(var lat: Double = 0.0, var long: Double = 0.0)