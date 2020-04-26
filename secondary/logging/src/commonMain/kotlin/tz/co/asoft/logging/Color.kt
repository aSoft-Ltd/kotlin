package tz.co.asoft.logging

internal enum class Color(val escape: String) {
    Red("\u001B[31m"),
    Maroon("\u001B[35m"),
    Yellow("\u001B[33m"),
    Blue("\u001B[36m"),
    Normal("\u001B[0m");
}