package tz.co.asoft.auth.usecase

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.toList
import tz.co.asoft.auth.User
import tz.co.asoft.auth.repo.IUsersRepo
import tz.co.asoft.auth.tools.hex.hex
import tz.co.asoft.email.Email
import tz.co.asoft.krypto.SHA256
import tz.co.asoft.phone.Phone
import tz.co.asoft.tools.Task
import tz.co.asoft.tools.emit
import tz.co.asoft.tools.flowTask

open class SignInUseCase(
    private val repo: IUsersRepo,
    private val userState: UserStateUseCase,
    private val updateStatusUC: UpdateStatusUseCase
) {
    @OptIn(ExperimentalStdlibApi::class)
    operator fun invoke(loginId: String, pwd: String) = flowTask<User> {
        coroutineScope {
            val progress = async { counter(0, 100, "Signing you in") }
            val login = async { loginTask(loginId, pwd) }
            val user = login.await()
            try {
                progress.cancel()
            } finally {
                emit(user)
            }
        }
    }

    private suspend fun FlowCollector<Task<User>>.counter(from: Int, to: Int, msg: String) {
        var index = from
        while (index < to) {
            delay(500)
            index += 10
            emit(index, msg)
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    private suspend fun loginTask(loginId: String, pwd: String): User {
        val xpwd = SHA256.digest(pwd.encodeToByteArray()).hex
        return if (loginId.contains("@")) {
            repo.load(Email(loginId), xpwd)
        } else {
            repo.load(Phone(loginId), xpwd)
        }?.also {
            userState.live.value = UserStateUseCase.State.LoggedIn(it)
            updateStatusUC(it, User.Status.SignedIn).toList()
        } ?: throw Exception("User not found")
    }
}