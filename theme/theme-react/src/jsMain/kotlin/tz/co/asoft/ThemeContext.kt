package tz.co.asoft

import kotlinx.coroutines.flow.MutableStateFlow
import react.RBuilder
import react.RHandler
import react.RProviderProps
import react.createContext
import kotlin.browser.document

val currentTheme = MutableStateFlow(AquaGreenTheme)

private val ThemeContext = createContext(currentTheme.value)

fun RBuilder.ThemeProvider(
    theme: Theme,
    handler: RHandler<RProviderProps<Theme>>
) = ThemeContext.Provider(theme, handler).apply {
    document.body?.style?.apply {
        backgroundColor = theme.backgroundColor.value
        color = theme.onBackgroundColor.value
    }
}

fun RBuilder.ThemeConsumer(handler: RBuilder.(Theme) -> Unit) = ThemeContext.Consumer(handler)
