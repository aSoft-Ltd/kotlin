package tz.co.asoft.auth.usecase

import tz.co.asoft.auth.User
import tz.co.asoft.auth.UserAccount
import tz.co.asoft.auth.exceptions.EmailExistException
import tz.co.asoft.auth.exceptions.PhoneExistException
import tz.co.asoft.auth.exceptions.UserAccountExistException
import tz.co.asoft.auth.repo.IUsersRepo
import tz.co.asoft.auth.tools.hex.hex
import tz.co.asoft.auth.tools.name.asName
import tz.co.asoft.email.Email
import tz.co.asoft.krypto.SHA256
import tz.co.asoft.persist.repo.IRepo
import tz.co.asoft.phone.Phone
import tz.co.asoft.tools.emit
import tz.co.asoft.tools.flowTask

open class RegisterUserUseCase(
    private val usersRepo: IUsersRepo,
    private val accountsRepo: IRepo<UserAccount>
) {

    @OptIn(ExperimentalStdlibApi::class)
    operator fun invoke(user: User, ua: UserAccount?) = flowTask<User> {
        emit(20, "Registering you")
        val preUser = user.copy(name = user.name.asName().formated())
        val account = ua ?: UserAccount(
            name = preUser.name,
            permits = preUser.permits
        )
        emit(40, "Creating Account")
        if (accountsRepo.load(account.uid) != null) {
            throw UserAccountExistException(account)
        }
        val uAccount = accountsRepo.create(account)
        val u = preUser.copy(
            password = SHA256.digest((preUser.password).encodeToByteArray()).hex,
            accounts = listOf(uAccount)
        )
        emit(60, "Verifying email")
        if (usersRepo.userWithEmailExists(u.emails)) throw EmailExistException(Email(u.emails.first()))
        emit(80, "Verifying phone")
        if (usersRepo.userWithPhoneExists(u.phones)) throw PhoneExistException(Phone(u.phones.first()))
        emit(usersRepo.create(u))
    }
}