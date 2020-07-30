@file:JvmName("JVMDocumentSnapshot")

package tz.co.asoft.firebase.firestore

import tz.co.asoft.firebase.firestore.DocumentSnapshot

actual open class DocumentSnapshot {

}

actual val DocumentSnapshot.id: String
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

actual fun DocumentSnapshot.get(fieldPath: String): Any? {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun DocumentSnapshot.data(): Map<String, Any>? {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun DocumentSnapshot.toJson(): String? {
    TODO("Not yet implemented")
}