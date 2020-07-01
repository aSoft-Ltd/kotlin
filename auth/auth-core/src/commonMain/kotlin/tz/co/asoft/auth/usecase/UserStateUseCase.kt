package tz.co.asoft.auth.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import tz.co.asoft.auth.User
import tz.co.asoft.auth.repo.IUsersRepo

class UserStateUseCase(
    private val repo: IUsersRepo
) : CoroutineScope by CoroutineScope(SupervisorJob()) {

    val live: MutableStateFlow<State> = MutableStateFlow(State.Unknown)

    sealed class State {
        object Unknown : State()
        object LoggedOut : State()
        class LoggedIn(val user: User) : State()
    }

    val user get() = (live.value as? State.LoggedIn)?.user

    init {
        launch {
            repo.loadLocalUser().let {
                live.value = if (it == null) {
                    State.LoggedOut
                } else {
                    State.LoggedIn(it)
                }
            }
        }
    }
}