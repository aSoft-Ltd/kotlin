package tz.co.asoft.firebase.firestore

import tz.co.asoft.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore as GFirebaseFirestore

actual typealias FirebaseFirestore = GFirebaseFirestore

actual val FirebaseFirestore.app: FirebaseApp
    get() = app

actual fun FirebaseFirestore.collection(path: String): CollectionReference {
    return collection(path)
}

actual fun FirebaseFirestore.document(path: String): DocumentReference {
    return document(path)
}

actual fun FirebaseApp.firestore(): FirebaseFirestore {
    return FirebaseFirestore.getInstance(this)
}

actual fun FirebaseFirestore.batch(): WriteBatch = batch()