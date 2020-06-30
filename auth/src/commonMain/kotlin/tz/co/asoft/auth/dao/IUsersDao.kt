package tz.co.asoft.auth.dao

import tz.co.asoft.auth.User
import tz.co.asoft.auth.UserAccount
import tz.co.asoft.email.Email
import tz.co.asoft.io.File
import tz.co.asoft.io.FileRef
import tz.co.asoft.persist.dao.IDao
import tz.co.asoft.phone.Phone

interface IUsersDao : IDao<User> {
    suspend fun uploadPhoto(user: User, photo: File): FileRef
    suspend fun load(email: Email, pwd: String): User?
    suspend fun load(phone: Phone, pwd: String): User?
    suspend fun loadUsers(ua: UserAccount): List<User>
    suspend fun userWithEmailExists(emails: List<String>): Boolean
    suspend fun userWithPhoneExists(phones: List<String>): Boolean
}