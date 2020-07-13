package tz.co.asoft.firebase.firestore

import kotlinx.serialization.KSerializer
import tz.co.asoft.persist.model.Entity

fun <T : Entity> FirestoreDao(
    firestore: FirebaseFirestore,
    collectionName: String,
    serializer: KSerializer<T>
): IFirebaseDao<T> = FirebaseDao(
    firestore,
    collectionName,
    serializer
)