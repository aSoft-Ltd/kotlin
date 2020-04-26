package tz.co.asoft.places.country

import kotlinx.serialization.Serializable

@Serializable
data class Currency(
        var code: String? = null,
        var name: String? = null,
        var symbol: String? = null
)