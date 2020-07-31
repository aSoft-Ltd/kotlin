package tz.co.asoft

expect class FirebaseFirestore

expect val FirebaseFirestore.app : FirebaseApp

expect fun FirebaseFirestore.collection(path: String): FirestoreCollectionReference

expect fun FirebaseFirestore.document(path: String): FirestoreDocumentReference

expect fun FirebaseFirestore.batch() : FirestoreWriteBatch

expect fun FirebaseApp.firestore() : FirebaseFirestore