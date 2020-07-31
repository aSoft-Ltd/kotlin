package tz.co.asoft

actual external class FirebaseOptions {
    var apiKey: String = definedExternally
    var authDomain: String = definedExternally
    var databaseURL: String? = definedExternally
    var projectId: String? = definedExternally
    var storageBucket: String? = definedExternally
    var messagingSenderId: String? = definedExternally
    var appId: String = definedExternally
}

actual var FirebaseOptions.apiKey: String
    get() = apiKey
    set(value) {
        apiKey = value
    }

actual var FirebaseOptions.authDomain: String
    get() = authDomain
    set(value) {
        authDomain = value
    }

actual var FirebaseOptions.databaseUrl: String?
    get() = databaseURL
    set(value) {
        databaseURL = value
    }

actual var FirebaseOptions.projectId: String?
    get() = projectId
    set(value) {
        projectId = value
    }

actual var FirebaseOptions.storageBucket: String?
    get() = storageBucket
    set(value) {
        storageBucket = value
    }

actual var FirebaseOptions.messagingSenderId: String?
    get() = messagingSenderId
    set(value) {
        messagingSenderId = value
    }

actual var FirebaseOptions.appId: String
    get() = appId
    set(value) {
        appId = value
    }


actual class FirebaseOptionsBuilder {
    @JsName("apiKey")
    var apiKey = ""

    @JsName("authDomain")
    var authDomain = ""

    @JsName("databaseURL")
    var databaseURL = ""

    @JsName("projectId")
    var projectId = ""

    @JsName("storageBucket")
    var storageBucket = ""

    @JsName("messagingSenderId")
    var messagingSenderId = ""

    @JsName("appId")
    var appId = ""
}

actual var FirebaseOptionsBuilder.apiKey: String
    get() = apiKey
    set(value) {
        apiKey = value
    }

actual var FirebaseOptionsBuilder.authDomain: String
    get() = authDomain
    set(value) {
        authDomain = value
    }

actual var FirebaseOptionsBuilder.databaseUrl: String
    get() = databaseURL
    set(value) {
        databaseURL = value
    }

actual var FirebaseOptionsBuilder.projectId: String
    get() = projectId
    set(value) {
        projectId = value
    }

actual var FirebaseOptionsBuilder.storageBucket: String
    get() = storageBucket
    set(value) {
        storageBucket = value
    }

actual var FirebaseOptionsBuilder.messagingSenderId: String
    get() = messagingSenderId
    set(value) {
        messagingSenderId = value
    }

actual var FirebaseOptionsBuilder.appId: String
    get() = appId
    set(value) {
        appId = value
    }

actual fun FirebaseOptionsBuilder.build(): FirebaseOptions = unsafeCast<FirebaseOptions>()