package tz.co.asoft.auth.usecase

import kotlinx.coroutines.flow.toList
import tz.co.asoft.auth.User
import tz.co.asoft.auth.repo.IUsersRepo
import tz.co.asoft.auth.tools.hex.hex
import tz.co.asoft.email.Email
import tz.co.asoft.krypto.SHA256
import tz.co.asoft.phone.Phone
import tz.co.asoft.tools.emit
import tz.co.asoft.tools.flowTask

open class SignInUseCase(
    private val repo: IUsersRepo,
    private val userState: UserStateUseCase,
    private val updateStatusUC: UpdateStatusUseCase
) {
    @OptIn(ExperimentalStdlibApi::class)
    operator fun invoke(loginId: String, pwd: String) = flowTask<User> {
        emit(10, "Singing you in")
        val xpwd = SHA256.digest(pwd.encodeToByteArray()).hex
        if (loginId.contains("@")) {
            repo.load(Email(loginId), xpwd)
        } else {
            repo.load(Phone(loginId), xpwd)
        }?.also {
            emit(it)
            userState.live.value = UserStateUseCase.State.LoggedIn(it)
            updateStatusUC(it, User.Status.SignedIn).toList()
        }
    }
}