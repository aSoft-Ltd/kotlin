package tz.co.asoft.places

import kotlinx.serialization.Serializable
import tz.co.asoft.places.country.Country

@Serializable
class Continent(var name: String = "") {
    var countries = mutableListOf<Country>()
}