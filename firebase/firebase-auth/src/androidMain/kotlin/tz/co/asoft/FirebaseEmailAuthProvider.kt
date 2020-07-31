package tz.co.asoft

actual class FirebaseEmailAuthProvider {
    actual companion object {
        actual fun getCredential(email: String, pwd: String): FirebaseAuthCredentials {
            return com.google.firebase.auth.EmailAuthProvider.getCredential(email, pwd)
        }
    }
}