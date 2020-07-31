package tz.co.asoft

external interface IEmailAuthProvider {
    fun credential(email: String, pwd: String): FirebaseAuthCredentials
}

@JsModule("firebase/auth")
@JsName("EmailAuthProvider")
private external val emailAuthProvider: IEmailAuthProvider

actual class FirebaseEmailAuthProvider {
    actual companion object {
        actual fun getCredential(email: String, pwd: String): FirebaseAuthCredentials {
            return emailAuthProvider.credential(email, pwd)
        }
    }
}