package tz.co.asoft.theme

import kotlinx.serialization.Serializable

@Serializable
data class ColorPallet(
    val primary: String,
    val primaryVariant: String,
    val onPrimary: String,
    val secondary: String,
    val secondaryVariant: String,
    val onSecondary: String,
    val background: String,
    val onBackground: String,
    val backgroundVariant: String,
    val onBackgroundVariant: String,
    val surface: String,
    val onSurface: String,
    val error: String,
    val onError: String
)