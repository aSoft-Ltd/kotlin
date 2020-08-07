package tz.co.asoft

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import react.*
import kotlin.browser.document

private class ThemeProvider(p: Props) : RComponent<ThemeProvider.Props, ThemeProvider.State>(p), CoroutineScope by CoroutineScope(SupervisorJob()) {
    class Props(
        val observerFrom: StateFlow<Theme>,
        val handler: RHandler<RProviderProps<Theme>>
    ) : RProps

    class State(var theme: Theme) : RState

    init {
        state = State(p.observerFrom.value)
    }

    override fun componentDidMount() {
        launch {
            props.observerFrom.collect {
                setState { theme = it }
            }
        }
    }

    override fun componentWillUnmount() {
        cancel()
    }

    override fun RBuilder.render(): dynamic = ThemeContext.Provider(state.theme, props.handler).apply {
        state.theme.imposeToDocument()
    }
}

private fun Theme.imposeToDocument() = document.body?.style?.also {
    it.backgroundColor = backgroundColor.value
    it.color = onBackgroundColor.value
}

fun RBuilder.ThemeProvider(
    observerFrom: StateFlow<Theme> = currentTheme,
    handler: RHandler<RProviderProps<Theme>>
) = child(ThemeProvider::class.js, ThemeProvider.Props(observerFrom, handler)) {}

fun RBuilder.ThemeProvider(
    theme: Theme,
    handler: RHandler<RProviderProps<Theme>>
) = ThemeContext.Provider(theme, handler).apply {
    theme.imposeToDocument()
}