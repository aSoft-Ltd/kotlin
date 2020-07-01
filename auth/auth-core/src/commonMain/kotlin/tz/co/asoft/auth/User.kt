package tz.co.asoft.auth

import kotlinx.serialization.Serializable
import tz.co.asoft.auth.tools.name.Name
import tz.co.asoft.auth.tools.name.asName
import tz.co.asoft.email.Email
import tz.co.asoft.klock.DateTime
import tz.co.asoft.neo4j.annotations.GeneratedValue
import tz.co.asoft.neo4j.annotations.Id
import tz.co.asoft.neo4j.annotations.NodeEntity
import tz.co.asoft.phone.Phone

@Serializable
@NodeEntity
data class User(
    @Id
    @GeneratedValue
    override var id: Long? = null,
    override var uid: String = "",
    override val name: String,
    val password: String,
    val username: String? = null,
    @Deprecated("Use permissions instead")
    val permits: List<String> = listOf(":settings", ":logs", ":profile"),
    val permissions: List<Permission> = listOf(),
    val scopes: List<String> = listOf(),
    val emails: List<String> = listOf(),
    val phones: List<String> = listOf(),
    val photoUrl: String? = null,
    val status: Status = Status.SignedOut,
    val accounts: List<UserAccount> = listOf(),
    val verifiedEmails: List<String> = listOf(),
    val verifiedPhones: List<String> = listOf(),
    val registeredOn: Long = DateTime.nowUnixLong(),
    val lastSeen: Long = DateTime.nowUnixLong(),
    override var deleted: Boolean = false
) : Claimer {

    init {
        if (emails.isEmpty()) {
            throw Exception("a User must have an email")
        } else {
            emails.forEach { Email(it) }
        }

        if (phones.isEmpty()) {
            throw Exception("a user must have a phone number")
        } else {
            phones.forEach { Phone(it) }
        }
    }

    enum class Status {
        Blocked,
        SignedIn,
        SignedOut,
        Deleted
    }

    fun ref() = UserRef(
        id = id,
        uid = uid,
        name = name,
        photoUrl = photoUrl
    )

    companion object {
        val fake: User
            get() {
                val name = Name.fake
                return User(
                    name = name,
                    username = name.asName().first.toLowerCase(),
                    password = "123456",
                    emails = listOf(Email("fake@fake.com").value),
                    phones = listOf(Phone.fake)
                )
            }
    }
}