package tz.co.asoft.places.street

import kotlinx.serialization.Serializable

@Serializable
class Street(
        var name: String = "",
        var wardName: String = ""
)