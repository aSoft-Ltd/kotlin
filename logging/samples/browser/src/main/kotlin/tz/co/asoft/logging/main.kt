package tz.co.asoft.logging

import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import tz.co.asoft.*
import kotlin.browser.document

fun main() {
    val div = (document.createElement("div") as HTMLDivElement).apply {
        val log = logger(verbose = false)
        val txt = input("Test Msg")
        val verbose = input("","checkbox")
        log.options = ConsoleAppenderOptions(verbose = verbose.checked,source = "Lamax")
        verbose.onchange = {
            log.options = ConsoleAppenderOptions(verbose = verbose.checked,source = "Lamax")
            undefined
        }
        button("Debug") {
            log.d(txt.value, "logger" to log)
        }

        button("Info") {
            log.i(
                txt.value,
                "logger" to log,
                "logger" to logger()
            )
        }

        button("Warn") {
            log.w(txt.value, "logger" to log)
        }

        button("Error") {
            log.e(txt.value, "logger" to log)
        }

        button("Failure") {
            log.f(txt.value, "logger" to log)
        }

        button("Dynamic Verbosity") {
            val opts = log.options
            log.options = ConsoleAppenderOptions(verbose = true)
            log.d(txt.value,"options" to log.options)
            log.options = ConsoleAppenderOptions(verbose = false)
        }
    }
    document.body?.appendChild(div)
}

fun HTMLElement.button(name: String, onClick: () -> Unit) {
    val bt = document.createElement("button") as HTMLButtonElement
    bt.innerHTML = name
    bt.onclick = { onClick() }
    appendChild(bt)
}

fun HTMLElement.input(text: String,typ: String = "text") = (document.createElement("input") as HTMLInputElement).apply {
    type=typ
    value = text
    this@input.appendChild(this)
}