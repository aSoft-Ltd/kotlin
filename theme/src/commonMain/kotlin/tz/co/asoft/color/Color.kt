package tz.co.asoft.color

inline class Color(val value: Int) {
    val r: Int get() = (value ushr 0) and 0xFF
    val g: Int get() = (value ushr 8) and 0xFF
    val b: Int get() = (value ushr 16) and 0xFF
    val a: Int get() = (value ushr 24) and 0xFF

    constructor(r: Int, g: Int, b: Int, a: Int) : this(0)
}