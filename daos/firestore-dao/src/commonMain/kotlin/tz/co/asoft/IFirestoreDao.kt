package tz.co.asoft

import kotlinx.serialization.KSerializer

interface IFirestoreDao<T : Entity> : IDao<T> {

    val firestore: FirebaseFirestore

    val serializer: KSerializer<T>

    val collectionName: String

    val batch get() = firestore.batch()

    val collection get() = firestore.collection(collectionName)

    fun docRef(id: String) = collection.doc(id)
}