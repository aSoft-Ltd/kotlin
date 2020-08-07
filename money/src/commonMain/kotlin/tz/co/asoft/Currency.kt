package tz.co.asoft

import kotlinx.serialization.Serializable

@Serializable
data class Currency(
    val name: String,
    val smallestUnitMultiplier: Int,
    val smallestUnit: String
) {
    companion object {
        val TZS = Currency("TZS", 100, "cents")
        val USD = Currency("USD", 100, "cents")
    }
}