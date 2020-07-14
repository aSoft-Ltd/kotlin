package tz.co.asoft.enterprise.panels

class NavMenu(val name: String, val link: String, val show: suspend () -> Boolean)