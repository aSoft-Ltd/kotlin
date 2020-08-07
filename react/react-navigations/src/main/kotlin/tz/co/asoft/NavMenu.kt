package tz.co.asoft

class NavMenu(val name: String, val link: String, val show: suspend () -> Boolean)