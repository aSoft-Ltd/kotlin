@file:JvmName("AndroidDocumentSnapshot")

package tz.co.asoft

import com.google.gson.Gson

actual typealias FirestoreDocumentSnapshot = com.google.firebase.firestore.DocumentSnapshot

actual fun FirestoreDocumentSnapshot.toJson(): String? = data()?.let { Gson().toJson(it) }

actual fun FirestoreDocumentSnapshot.get(fieldPath: String): Any? = get(fieldPath)
actual fun FirestoreDocumentSnapshot.data(): Map<String, Any>? = data
actual val FirestoreDocumentSnapshot.id: String
    get() = id