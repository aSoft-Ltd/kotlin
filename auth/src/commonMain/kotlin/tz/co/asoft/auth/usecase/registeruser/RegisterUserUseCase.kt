package tz.co.asoft.auth.usecase.registeruser

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
import tz.co.asoft.persist.result.catching
import tz.co.asoft.phone.Phone

open class RegisterUserUseCase(
    private val usersRepo: IUsersRepo,
    private val accountsRepo: IRepo<UserAccount>
) : IRegisterUserUseCase {

    @OptIn(ExperimentalStdlibApi::class)
    override suspend operator fun invoke(user: User, ua: UserAccount?) = catching {
        val preUser = user.copy(name = user.name.asName().formated())
        val account = ua ?: UserAccount(
            name = preUser.name,
            permits = preUser.permits
        )
        if (accountsRepo.load(account.uid) != null) {
            throw UserAccountExistException(account)
        }
        val uAccount = accountsRepo.create(account)
        val u = preUser.copy(
            password = SHA256.digest((preUser.password).encodeToByteArray()).hex,
            accounts = listOf(uAccount)
        )
        if (usersRepo.userWithEmailExists(u.emails)) throw EmailExistException(Email(u.emails.first()))
        if (usersRepo.userWithPhoneExists(u.phones)) throw PhoneExistException(Phone(u.phones.first()))
        usersRepo.create(u)
    }
}