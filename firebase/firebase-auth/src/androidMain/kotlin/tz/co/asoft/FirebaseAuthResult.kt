package tz.co.asoft

import com.google.firebase.auth.AuthResult as GAuthResult

actual typealias FirebaseAuthResult = GAuthResult

actual val FirebaseAuthResult.user get() = user