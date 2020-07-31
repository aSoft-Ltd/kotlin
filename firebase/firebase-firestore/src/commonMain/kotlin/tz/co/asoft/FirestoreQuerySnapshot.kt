package tz.co.asoft

import kotlinx.serialization.KSerializer

expect class FirestoreQuerySnapshot

expect val FirestoreQuerySnapshot.documents: List<FirestoreQueryDocumentSnapshot>

expect fun FirestoreQuerySnapshot.forEach(callback: (FirestoreQueryDocumentSnapshot) -> Unit)

fun <T : Any> FirestoreQuerySnapshot.toObjects(serializer: KSerializer<T>): List<T> =
    documents.mapNotNull { it.toObject(serializer) }