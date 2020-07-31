package tz.co.asoft

import com.google.firebase.firestore.FirebaseFirestore as GFirebaseFirestore

actual typealias FirebaseFirestore = GFirebaseFirestore

actual val FirebaseFirestore.app: FirebaseApp
    get() = app

actual fun FirebaseFirestore.collection(path: String): FirestoreCollectionReference {
    return collection(path)
}

actual fun FirebaseFirestore.document(path: String): FirestoreDocumentReference {
    return document(path)
}

actual fun FirebaseApp.firestore(): FirebaseFirestore {
    return FirebaseFirestore.getInstance(this)
}

actual fun FirebaseFirestore.batch(): FirestoreWriteBatch = batch()