package tz.co.asoft

class Listener<T>(val next: (T) -> Unit, val error: (Any) -> Unit)