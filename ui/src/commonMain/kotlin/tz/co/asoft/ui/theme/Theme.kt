package tz.co.asoft.ui.theme

import kotlin.js.JsName

@JsName("Theme")
open class Theme {
    var name = "Default Blue Theme"
    var primaryColor = ThemeColors("#539cff", "#0066cc", "#003d9a")
    var secondaryColor = ThemeColors("#6ceb60", "#2eb82e", "#088700")
    var tertiaryColor = ThemeColors("#ffff5a", "#ffff00", "#c7cc00")
    var backgroundColor = ThemeColors("#ffffff", "#f5f5f5", "#c2c2c2")
    var error = ThemeColors(main = "#ff0000")
    var alternateColor = arrayOf(ThemeColors(main = "#253646"))

    var text = TypoColors()

    companion object {
        object AquaGreen2Theme : Theme() {
            init {
                name = "Aqua Green 2 Theme"
                primaryColor = ThemeColors("#56cdba", "#0E9B8A", "#006c5d")
                secondaryColor = ThemeColors("#5cdec8", "#12AC97", "#007c69")
                tertiaryColor = primaryColor
                alternateColor = arrayOf(ThemeColors("#002618"))

                text.apply {
                    onPrimary = ThemeColors("#121212", "#DADADA", "#DADADA")
                    onSecondary = ThemeColors("#121212", "#121212", "#DADADA")
                    onTertiary = onPrimary
                }
            }
        }

        object AquaGreenTheme : Theme() {
            init {
                name = "Aqua Green Theme"
                primaryColor = ThemeColors("#397c69", "#007c69", "#002618")
                secondaryColor = ThemeColors("#5cdec8", "#12AC97", "#007c69")
                tertiaryColor = primaryColor
                alternateColor = arrayOf(ThemeColors("#002618"))

                text.apply {
                    onPrimary = ThemeColors("#121212", "#DADADA", "#DADADA")
                    onSecondary = ThemeColors("#121212", "#121212", "#DADADA")
                    onTertiary = onPrimary
                }
            }
        }
    }
}
