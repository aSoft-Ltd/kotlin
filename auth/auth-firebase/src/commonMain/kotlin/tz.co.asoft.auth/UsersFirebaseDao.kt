package tz.co.asoft.auth

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import tz.co.asoft.auth.dao.IUsersDao
import tz.co.asoft.email.Email
import tz.co.asoft.firebase.firestore.FirebaseDao
import tz.co.asoft.firebase.firestore.FirebaseFirestore
import tz.co.asoft.firebase.firestore.query.fetch
import tz.co.asoft.firebase.firestore.query.where
import tz.co.asoft.firebase.firestore.snapshot.documents
import tz.co.asoft.firebase.firestore.snapshot.toObject
import tz.co.asoft.firebase.storage.*
import tz.co.asoft.io.File
import tz.co.asoft.io.FileRef
import tz.co.asoft.io.ext
import tz.co.asoft.phone.Phone

class UsersFirebaseDao(
    firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) : FirebaseDao<User>(firestore, "users", User.serializer()), IUsersDao {
    private suspend fun load(method: String, key: String, pwd: String): User? {
        val qs = collection.where("${method}s", "array-contains", key).fetch()
        val user = qs.documents.getOrNull(0)?.toObject(serializer)
            ?: throw Exception("User not found")
        if (user.password == pwd) {
            return user
        } else {
            throw Exception("Incorrect Password")
        }
    }

    override suspend fun load(email: Email, pwd: String): User? {
        return load("email", email.value, pwd)
    }

    override suspend fun load(phone: Phone, pwd: String): User? {
        return load("phone", phone.value, pwd)
    }

    override suspend fun loadUsers(ua: UserAccount): List<User> {
        return all().filter { a -> a.accounts.map { it.uid }.contains(ua.uid) }
    }

    private suspend fun userWithMethodExists(method: String, list: List<String>) = coroutineScope {
        list.map {
            async {
                val query = collection.where("${method}s", "array-contains", it)
                query.fetch().documents
            }
        }.map { it.await() }.flatten().isNotEmpty()
    }

    override suspend fun userWithEmailExists(emails: List<String>) =
        userWithMethodExists("email", emails)

    override suspend fun userWithPhoneExists(phones: List<String>) =
        userWithMethodExists("phone", phones)

    override suspend fun delete(list: Collection<User>): List<User> {
        return edit(list.map { it.copy(status = User.Status.Deleted) })
    }

    override suspend fun delete(t: User): User = delete(listOf(t)).first()

    override suspend fun uploadPhoto(user: User, photo: File): FileRef {
        val ref = storage.ref("user_profile_photos/${user.uid}${photo.ext}")
        ref.upload(photo).await()
        return FileRef(
            name = "profile_photo" + photo.ext,
            url = ref.downloadUrl() ?: throw Exception("Failed to Upload Photo")
        )
    }
}