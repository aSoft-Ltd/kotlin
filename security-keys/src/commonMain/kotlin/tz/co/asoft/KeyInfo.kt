package tz.co.asoft

interface KeyInfo {
    var uid: String?
    val created: Long
    val expires: Long
}