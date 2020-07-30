package tz.co.asoft.firebase.firestore

class Listener<T>(val next: (T) -> Unit, val error: (Any) -> Unit)