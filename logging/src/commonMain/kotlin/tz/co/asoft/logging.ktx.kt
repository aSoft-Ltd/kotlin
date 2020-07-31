package tz.co.asoft

fun Logger.tagged(tag: String) = also {
    it.tag = tag
}