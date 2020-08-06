package tz.co.asoft

import org.w3c.dom.Document
import kotlin.js.Promise

external interface FontSourceSet {
    val ready: Promise<Any>
    fun check(font: String): Boolean
}

inline val Document.fonts: FontSourceSet get() = asDynamic()["fonts"]