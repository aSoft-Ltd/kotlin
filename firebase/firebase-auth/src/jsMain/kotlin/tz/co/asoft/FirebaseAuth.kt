package tz.co.asoft

import kotlinx.coroutines.await
import kotlin.js.Promise

@JsModule("firebase/auth")
private external val authLib: dynamic

actual external class FirebaseAuth {
    val app: FirebaseApp
    val currentUser: FirebaseUser?
    fun createUserWithEmailAndPassword(email: String, password: String): Promise<FirebaseAuthResult>
    fun signInWithEmailAndPassword(email: String, password: String): Promise<FirebaseAuthResult>
    fun signOut(): Promise<Unit>
}

actual val FirebaseAuth.app: FirebaseApp get() = app
actual val FirebaseAuth.currentUser get() = currentUser
actual suspend fun FirebaseAuth.createUserWithEmailAndPassword(
    email: String,
    password: String,
    then: (FirebaseAuthResult) -> Unit
) {
    val res = createUserWithEmailAndPassword(email, password).await()
    then(res)
}

actual suspend fun FirebaseAuth.signInWithEmailAndPassword(
    email: String,
    password: String,
    then: (FirebaseAuthResult) -> Unit
) {
    val res = signInWithEmailAndPassword(email, password).await()
    then(res)
}

actual suspend fun FirebaseAuth.signOut() {
    signOut().await()
}

actual fun FirebaseApp.auth(): FirebaseAuth {
    if (authLib.isImported != true) {
        authLib.isImported = true
    }
    return unsafeCast<dynamic>().auth()
}

actual suspend fun FirebaseAuth.makeUserWithEmailAndPassword(
    email: String,
    password: String
): FirebaseAuthResult {
    return createUserWithEmailAndPassword(email, password).await()
}

actual suspend fun FirebaseAuth.loginUserWithEmailAndPassword(
    email: String,
    password: String
): FirebaseAuthResult {
    return signInWithEmailAndPassword(email, password).await()
}

actual suspend fun FirebaseAuth.logout() {
    signOut().await()
}