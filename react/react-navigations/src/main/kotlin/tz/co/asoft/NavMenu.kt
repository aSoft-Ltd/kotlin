package tz.co.asoft

import react.RClass

class NavMenu(val name: String, val link: String, val icon: RClass<IconProps>, val show: suspend () -> Boolean)