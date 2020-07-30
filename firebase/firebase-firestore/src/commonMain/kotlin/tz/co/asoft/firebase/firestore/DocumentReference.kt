package tz.co.asoft.firebase.firestore

import kotlinx.serialization.KSerializer

expect class DocumentReference

expect val DocumentReference.firestore: FirebaseFirestore
expect val DocumentReference.id: String
expect fun DocumentReference.collection(path: String): CollectionReference

expect suspend fun <T> DocumentReference.set(data: T, serializer: KSerializer<T>, then: suspend () -> Unit = {})

expect suspend fun <T : Any> DocumentReference.put(data: T, serializer: KSerializer<T>)

expect suspend fun DocumentReference.fetch(): DocumentSnapshot

expect fun DocumentReference.addListener(listener: (DocumentSnapshot) -> Unit)