package tz.co.asoft.enterprise.progress

import kotlinx.css.*
import kotlinx.css.properties.boxShadowInset
import react.*
import styled.css
import styled.styledProgress
import tz.co.asoft.enterprise.progress.ProgressBar.Props
import tz.co.asoft.enterprise.progress.ProgressBar.State
import tz.co.asoft.theme.ThemeConsumer
import tz.co.asoft.theme.primaryColor
import tz.co.asoft.theme.primaryVariantColor
import tz.co.asoft.tools.onDesktop
import tz.co.asoft.tools.onMobile
import kotlin.browser.window

private class ProgressBar(p: Props) : RComponent<Props, State>(p) {
    class Props(val value: Number?) : RProps

    class State : RState {
        var dir = 1
        var value = 0.0
    }

    init {
        state = State()
    }

    private var intervalId: Int = -1

    private fun nextIndeterminateValue() {
        intervalId = window.setTimeout({
            setState {
                if (value + dir > 100 || value + dir < 0) {
                    dir *= -1
                }
                value += dir
            }
        }, 10)
    }

    override fun componentWillUnmount() {
        window.clearInterval(intervalId)
    }

    override fun RBuilder.render(): dynamic = ThemeConsumer { theme ->
        styledProgress {
            css {
                put("appearance", "none")
                val decs = arrayOf("-webkit-", "-moz-", "")
                decs.forEach {
                    "&::${it}progress-bar" {
                        backgroundColor = theme.primaryColor
                        borderRadius = 2.px
                        boxShadowInset(rgba(0, 0, 0, 0.25), blurRadius = 5.px, spreadRadius = 2.px)
                    }
                }

                decs.forEach {
                    "&::${it}progress-value" {
                        backgroundColor = theme.primaryVariantColor
                        borderRadius = 2.px
                    }
                }
                onDesktop {
                    height = 5.px
                }

                onMobile {
                    height = 10.px
                }
            }
            attrs {
                value = (props.value ?: state.value.apply {
                    nextIndeterminateValue()
                }).toString()
                max = "100"
            }
        }
    }
}

fun RBuilder.ProgressBar(value: Number? = null) = child(ProgressBar::class.js, Props(value)) {}