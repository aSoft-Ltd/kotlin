package tz.co.asoft.places.country

import kotlinx.serialization.Serializable

@Serializable
data class RegionalBloc(
        var acronym: String? = null,
        var name: String? = null,
        var otherAcronyms: MutableList<String> = mutableListOf(),
        var otherNames: MutableList<String> = mutableListOf()
)