package tz.co.asoft.theme

import kotlinx.css.*

val Theme.primaryColor get() = Color(color.primary.toHexString())
val Theme.primaryVariantColor get() = Color(color.primaryVariant.toHexString())
val Theme.onPrimaryColor get() = Color(color.onPrimary.toHexString())
val Theme.secondaryColor get() = Color(color.secondary.toHexString())
val Theme.secondaryVariantColor get() = Color(color.secondaryVariant.toHexString())
val Theme.onSecondaryColor get() = Color(color.onSecondary.toHexString())
val Theme.backgroundColor get() = Color(color.background.toHexString())
val Theme.onBackgroundColor get() = Color(color.onBackground.toHexString())
val Theme.backgroundVariantColor get() = Color(color.backgroundVariant.toHexString())
val Theme.onBackgroundVariantColor get() = Color(color.onBackgroundVariant.toHexString())
val Theme.surfaceColor get() = Color(color.surface.toHexString())
val Theme.onSurfaceColor get() = Color(color.onSurface.toHexString())
val Theme.errorColor get() = Color(color.error.toHexString())
val Theme.onErrorColor get() = Color(color.onError.toHexString())

val TextStyle.clazz: RuleSet
    get() = {
        fontSize = this@clazz.fontSize
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
