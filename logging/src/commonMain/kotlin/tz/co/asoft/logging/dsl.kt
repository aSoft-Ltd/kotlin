package tz.co.asoft.logging

fun Logger.tagged(tag: String) = also {
    it.tag = tag
}