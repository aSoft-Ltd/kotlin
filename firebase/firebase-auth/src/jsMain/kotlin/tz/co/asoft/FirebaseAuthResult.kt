package tz.co.asoft

actual external interface FirebaseAuthResult {
    var user: FirebaseUser?
}

actual val FirebaseAuthResult.user get() = user