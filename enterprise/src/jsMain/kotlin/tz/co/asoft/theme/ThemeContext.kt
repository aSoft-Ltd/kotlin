package tz.co.asoft.theme

import kotlinx.coroutines.flow.MutableStateFlow
import react.RBuilder
import react.RHandler
import react.RProviderProps
import react.createContext

val currentTheme = MutableStateFlow(AquaGreenTheme)

private val ThemeContext = createContext(currentTheme.value)

fun RBuilder.ThemeProvider(
    theme: Theme,
    handler: RHandler<RProviderProps<Theme>>
) = ThemeContext.Provider(theme, handler)

fun RBuilder.ThemeConsumer(handler: RBuilder.(Theme) -> Unit) = ThemeContext.Consumer(handler)
