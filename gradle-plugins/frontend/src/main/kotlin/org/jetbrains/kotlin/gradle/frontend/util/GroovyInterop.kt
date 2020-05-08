package org.jetbrains.kotlin.gradle.frontend.util

import groovy.lang.Closure
import org.gradle.internal.Cast.uncheckedCast

fun <T> Any.delegateClosureOf(action: T.() -> Unit) =
    object : Closure<Unit>(this, this) {
        @Suppress("unused") // to be called dynamically by Groovy
        fun doCall() = uncheckedCast<T>(delegate)?.action()
    }