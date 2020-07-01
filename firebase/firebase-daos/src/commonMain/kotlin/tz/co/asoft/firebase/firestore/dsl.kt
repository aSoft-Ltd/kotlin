package tz.co.asoft.firebase.firestore

import kotlinx.serialization.KSerializer
import tz.co.asoft.firebase.firestore.query.QueryParam
import tz.co.asoft.persist.model.Entity
import kotlin.reflect.KProperty

fun <T : Entity> FirestoreDao(
    firestore: FirebaseFirestore,
    collectionName: String,
    serializer: KSerializer<T>
): IFirebaseDao<T> = FirebaseDao(
    firestore,
    collectionName,
    serializer
)