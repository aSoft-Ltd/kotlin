package tz.co.asoft.auth.usecase

import kotlinx.coroutines.flow.*
import tz.co.asoft.auth.User
import tz.co.asoft.auth.repo.IUsersRepo
import tz.co.asoft.tools.Task
import tz.co.asoft.tools.emit
import tz.co.asoft.tools.flowTask
import tz.co.asoft.tools.map
import kotlin.coroutines.coroutineContext

open class SignOutUseCase(
    private val repo: IUsersRepo,
    private val userStateUC: UserStateUseCase,
    private val updateStatusUC: UpdateStatusUseCase
) {
    operator fun invoke() = flowTask<Unit> {
        when (val state = userStateUC.live.value) {
            is UserStateUseCase.State.LoggedIn -> {
                emit(50)
                repo.deleteLocal()
                userStateUC.live.value = UserStateUseCase.State.LoggedOut
                updateStatusUC(state.user, User.Status.SignedOut).collect()
            }
            else -> emit(Unit)
        }
    }
}