package tz.co.asoft.ui.react.tools

import react.*
import kotlin.browser.document

open class ViewHolder {
    fun <T> byId(id: String) = Element<T>(id)

    class Element<T>(val id: String) {
        val element get() = document.getElementById(id).unsafeCast<T>()
    }
}