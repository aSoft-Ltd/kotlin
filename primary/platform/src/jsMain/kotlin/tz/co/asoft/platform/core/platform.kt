package tz.co.asoft.platform.core

actual val platform: Platform get() = Platform.JavaScript

external fun <T> require(module: String): T