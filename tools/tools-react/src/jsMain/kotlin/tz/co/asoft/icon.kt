package tz.co.asoft

import org.w3c.dom.HTMLLinkElement
import kotlin.browser.document

fun newIcon(url: String) {
    val link = document.querySelector("link[rel*='icon']") ?: document.createElement("link")
    (link as? HTMLLinkElement)?.apply {
        type = "image/x-icon"
        rel = "shortcut icon"
        href = url
    }
    document.head?.apply {
        removeChild(link)
        append(link)
    }
}