package tz.co.asoft.tools

class Action<T>(val name: String,val handler: (T) -> Unit)