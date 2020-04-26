package tz.co.asoft.places.country

import kotlinx.serialization.Serializable

@Serializable
data class Translations(
        var br: String? = null,
        var de: String? = null,
        var es: String? = null,
        var fa: String? = null,
        var fr: String? = null,
        var hr: String? = null,
        var `it`: String? = null,
        var ja: String? = null,
        var nl: String? = null,
        var pt: String? = null
)