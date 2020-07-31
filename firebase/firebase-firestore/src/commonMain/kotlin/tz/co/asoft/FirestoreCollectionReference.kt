package tz.co.asoft

import kotlinx.serialization.KSerializer

expect class FirestoreCollectionReference : FirestoreQuery

expect val FirestoreCollectionReference.firestore: FirebaseFirestore
expect val FirestoreCollectionReference.id: String
expect val FirestoreCollectionReference.parent: FirestoreDocumentReference?
expect val FirestoreCollectionReference.path: String
expect fun FirestoreCollectionReference.doc(documentPath: String? = null): FirestoreDocumentReference
expect fun FirestoreCollectionReference.addListener(listener: (FirestoreQuerySnapshot) -> Unit)
expect suspend fun <T : Any> FirestoreCollectionReference.put(data: T, serializer: KSerializer<T>): FirestoreDocumentReference