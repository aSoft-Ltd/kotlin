package tz.co.asoft

expect class FirebaseStorage

expect val FirebaseStorage.app: FirebaseApp
expect fun FirebaseStorage.ref(path: String? = null): FirebaseStorageReference

expect fun FirebaseApp.storage() : FirebaseStorage