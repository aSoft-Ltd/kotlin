package tz.co.asoft.firebase.firestore

import kotlinx.serialization.KSerializer
import tz.co.asoft.Entity
import tz.co.asoft.IDao

interface IFirebaseDao<T : Entity> : IDao<T> {

    val firestore: FirebaseFirestore

    val serializer: KSerializer<T>

    val collectionName: String

    val batch get() = firestore.batch()

    val collection get() = firestore.collection(collectionName)

    fun docRef(id: String) = collection.doc(id)
}