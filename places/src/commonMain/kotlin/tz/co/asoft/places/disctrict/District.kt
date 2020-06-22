package tz.co.asoft.places.disctrict

import kotlinx.serialization.Serializable
import tz.co.asoft.places.ward.Ward

@Serializable
class District(
        var name: String = "",
        var regionName: String = "",
        var countryName: String = "",
        var wards: MutableList<Ward> = mutableListOf()
)