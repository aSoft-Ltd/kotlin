package tz.co.asoft.theme.tools

import tz.co.asoft.color.color
import tz.co.asoft.theme.ColorPalette
import androidx.ui.material.ColorPalette as MaterialColorPalette

val ColorPalette.palette: MaterialColorPalette
    get() = object : MaterialColorPalette {
        override val background = this@palette.background.color
        override val error = this@palette.error.color
        override val isLight = this@palette.isLight
        override val onBackground = this@palette.onBackground.color
        override val onError = this@palette.onError.color
        override val onPrimary = this@palette.onPrimary.color
        override val onSecondary = this@palette.onSecondary.color
        override val onSurface = this@palette.onSurface.color
        override val primary = this@palette.primary.color
        override val primaryVariant = this@palette.primaryVariant.color
        override val secondary = this@palette.secondary.color
        override val secondaryVariant = this@palette.secondaryVariant.color
        override val surface = this@palette.surface.color
    }