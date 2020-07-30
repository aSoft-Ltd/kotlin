package tz.co.asoft

/**
 * Exposes the firebase js
 */
@JsModule("firebase/app")
@JsName("default")
external object Firebase {
    fun initializeApp(options: FirebaseOptions, name: String? = definedExternally): FirebaseApp
}