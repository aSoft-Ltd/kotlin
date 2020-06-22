package tz.co.asoft.tools

class Action<in T>(val name: String, val handler: suspend (T) -> Unit = {})