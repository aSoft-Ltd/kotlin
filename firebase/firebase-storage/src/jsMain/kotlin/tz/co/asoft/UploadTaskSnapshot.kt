package tz.co.asoft

external class TaskSnapshot {
    val bytesTransferred: Number
    val downloadURL: String?
    val ref: FirebaseStorageReference
    val task: FirebaseStorageUploadTask
    val totalBytes: Number
}

class JSTaskSnapshot(private val snapshot: TaskSnapshot) : FirebaseStorageUploadTaskSnapshot() {
    override val bytesTransferred get() = "${snapshot.bytesTransferred}".toLong()
    override val downloadURL get() = snapshot.toString()
    override val totalBytes get() = "${snapshot.totalBytes}".toLong()
}