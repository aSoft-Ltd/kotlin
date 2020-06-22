package tz.co.asoft.places.ward

import kotlinx.serialization.Serializable
import tz.co.asoft.places.street.Street

@Serializable
class Ward(var name: String = "",
           var districtName: String = "",
           var regionName: String = "",
           var countryName: String = "",
           var streets: MutableList<Street> = mutableListOf()
)