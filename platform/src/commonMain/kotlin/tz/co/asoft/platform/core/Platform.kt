package tz.co.asoft.platform.core

sealed class Platform(val name: String) {
    object Android : Platform("Android")
    object JavaScript : Platform("JavaScript")
    object Jvm : Platform("Jvm")
    object Native : Platform("Native")
}

expect val platform: Platform