package tz.co.asoft.ui.theme

class ThemeColors(val light: String = "#ffffff", val main: String = light, val dark: String = main) {
    constructor(main: String) : this(light = main)
}

class TypoColors {
    var onPrimary = ThemeColors("#111111", "#f5f5f5", "#ffffff")
    var onSecondary = ThemeColors(main = "#111111")
    var onTertiary = ThemeColors(main = "#111111")
    var onBackground = ThemeColors("#253646", "#253646", "#111111")
    var onAlternate = arrayListOf(ThemeColors(main = "#f5f5f5"))
}