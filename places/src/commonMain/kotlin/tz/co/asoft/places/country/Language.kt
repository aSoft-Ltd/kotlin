package tz.co.asoft.places.country

import kotlinx.serialization.Serializable

@Serializable
data class Language(
        var iso639_1: String? = null,
        var iso639_2: String? = null,
        var name: String? = null,
        var nativeName: String? = null
)