package tz.co.asoft

import kotlinx.serialization.KSerializer

expect class FirestoreDocumentReference

expect val FirestoreDocumentReference.firestore: FirebaseFirestore
expect val FirestoreDocumentReference.id: String
expect fun FirestoreDocumentReference.collection(path: String): FirestoreCollectionReference

expect suspend fun <T> FirestoreDocumentReference.set(data: T, serializer: KSerializer<T>, then: suspend () -> Unit = {})

expect suspend fun <T : Any> FirestoreDocumentReference.put(data: T, serializer: KSerializer<T>)

expect suspend fun FirestoreDocumentReference.fetch(): FirestoreDocumentSnapshot

expect fun FirestoreDocumentReference.addListener(listener: (FirestoreDocumentSnapshot) -> Unit)