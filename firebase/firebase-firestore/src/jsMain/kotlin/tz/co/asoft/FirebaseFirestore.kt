package tz.co.asoft

@JsModule("firebase/firestore/memory")
private external val firestoreLib: dynamic

actual external class FirebaseFirestore {
    val app: FirebaseApp

    fun collection(path: String): FirestoreCollectionReference

    @JsName("doc")
    fun document(path: String): FirestoreDocumentReference

    fun batch(): FirestoreWriteBatch
}

actual val FirebaseFirestore.app: FirebaseApp
    get() = app

actual fun FirebaseFirestore.collection(path: String): FirestoreCollectionReference {
    return collection(path)
}

actual fun FirebaseFirestore.document(path: String): FirestoreDocumentReference {
    return document(path)
}

actual fun FirebaseApp.firestore(): FirebaseFirestore {
    if (firestoreLib.isImported != true) {
        firestoreLib.isImported = true
    }
    return unsafeCast<dynamic>().firestore()
}

actual fun FirebaseFirestore.batch(): FirestoreWriteBatch = batch()