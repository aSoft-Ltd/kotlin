package tz.co.asoft.theme

import kotlinx.css.*

val Theme.primaryColor get() = Color(color.primary)
val Theme.primaryVariantColor get() = Color(color.primaryVariant)
val Theme.onPrimaryColor get() = Color(color.onPrimary)
val Theme.secondaryColor get() = Color(color.secondary)
val Theme.secondaryVariantColor get() = Color(color.secondaryVariant)
val Theme.onSecondaryColor get() = Color(color.onSecondary)
val Theme.backgroundColor get() = Color(color.background)
val Theme.onBackgroundColor get() = Color(color.onBackground)
val Theme.backgroundVariantColor get() = Color(color.backgroundVariant)
val Theme.onBackgroundVariantColor get() = Color(color.onBackgroundVariant)
val Theme.surfaceColor get() = Color(color.surface)
val Theme.onSurfaceColor get() = Color(color.onSurface)
val Theme.errorColor get() = Color(color.error)
val Theme.onErrorColor get() = Color(color.onError)

val TextStyle.clazz: RuleSet
    get() = {
        fontSize = this@clazz.fontSize.rem
    }

val Theme.dropdown_clazz: RuleSet
    get() = {
        outline = Outline.none
        color = Color.inherit
        child("option") {
            backgroundColor = this@dropdown_clazz.backgroundColor
            color = onBackgroundColor
            hover {
                backgroundColor = this@dropdown_clazz.primaryColor
                color = onPrimaryColor
            }
        }
    }