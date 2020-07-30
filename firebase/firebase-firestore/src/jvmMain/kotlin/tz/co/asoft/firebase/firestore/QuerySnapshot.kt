@file:JvmName("QuerySnapshotAndroid")

package tz.co.asoft.firebase.firestore

import tz.co.asoft.firebase.firestore.QueryDocumentSnapshot
import tz.co.asoft.firebase.firestore.QuerySnapshot

actual class QuerySnapshot

actual val QuerySnapshot.documents: List<QueryDocumentSnapshot>
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

actual fun QuerySnapshot.forEach(callback: (QueryDocumentSnapshot) -> Unit) {
}