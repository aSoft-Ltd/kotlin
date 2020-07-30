package tz.co.asoft.firebase.firestore

import kotlinx.serialization.KSerializer

expect class CollectionReference : Query

expect val CollectionReference.firestore: FirebaseFirestore
expect val CollectionReference.id: String
expect val CollectionReference.parent: DocumentReference?
expect val CollectionReference.path: String
expect fun CollectionReference.doc(documentPath: String? = null): DocumentReference
expect fun CollectionReference.addListener(listener: (QuerySnapshot) -> Unit)
expect suspend fun <T : Any> CollectionReference.put(data: T, serializer: KSerializer<T>): DocumentReference