package tz.co.asoft

actual typealias FirebaseApp = com.google.firebase.FirebaseApp

actual val FirebaseApp.name: String
    get() = name

actual val FirebaseApp.options: FirebaseOptions
    get() = options