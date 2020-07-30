@file:JvmName("AndroidDocumentSnapshot")

package tz.co.asoft.firebase.firestore

import com.google.gson.Gson

actual typealias DocumentSnapshot = com.google.firebase.firestore.DocumentSnapshot

actual fun DocumentSnapshot.toJson(): String? = data()?.let { Gson().toJson(it) }

actual fun DocumentSnapshot.get(fieldPath: String): Any? = get(fieldPath)
actual fun DocumentSnapshot.data(): Map<String, Any>? = data
actual val DocumentSnapshot.id: String
    get() = id