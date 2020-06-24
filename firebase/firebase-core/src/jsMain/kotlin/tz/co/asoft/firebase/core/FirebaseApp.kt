package tz.co.asoft.firebase.core

actual external class FirebaseApp {
    val name: String
    val options: FirebaseOptions
}

actual val FirebaseApp.name: String
    get() = name

actual val FirebaseApp.options: FirebaseOptions
    get() = options