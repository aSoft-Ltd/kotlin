package tz.co.asoft.theme

import kotlinx.serialization.Serializable

@Serializable
data class Theme(
    val name: String,
    val color: ColorPallet,
    val text: Typography
)