package tz.co.asoft

expect class FirebaseEmailAuthProvider {
    companion object {
        fun getCredential(email: String, pwd: String): FirebaseAuthCredentials
    }
}