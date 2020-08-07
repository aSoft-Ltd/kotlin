package tz.co.asoft

class Action<T>(val name: String,val handler: (T) -> Unit)