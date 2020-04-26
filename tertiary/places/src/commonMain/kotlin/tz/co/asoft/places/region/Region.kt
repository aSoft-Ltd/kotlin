package tz.co.asoft.places.region

import kotlinx.serialization.Serializable
import tz.co.asoft.places.disctrict.District

@Serializable
class Region(
        var id: Int = 0,
        var name: String = "",
        var countryName: String = "",
        var districts: MutableList<District> = mutableListOf()
)