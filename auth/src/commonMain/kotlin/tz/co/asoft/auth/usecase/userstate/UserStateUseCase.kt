package tz.co.asoft.auth.usecase.userstate

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import tz.co.asoft.auth.User
import tz.co.asoft.auth.repo.IUsersRepo
import tz.co.asoft.rx.LiveData

class UserStateUseCase(private val repo: IUsersRepo) : IUserStateUseCase {
    private val stateScope = CoroutineScope(SupervisorJob())

    @Deprecated("Use the one with state flow")
    override val liveUser = LiveData<User?>(null)
    override val live: MutableStateFlow<User?> = MutableStateFlow(null)

    init {
        stateScope.launch {
            repo.loadLocalUser()?.let { liveUser.value = it }
        }
        liveUser.onChange(stateScope) {
            live.value = it
        }
    }

    @Deprecated("Try collecting on the live member")
    override fun onChange(scope: CoroutineScope, action: (User?) -> Unit) {
        if (liveUser.value == null) {
            scope.launch { currentUser()?.let { liveUser.value = it } }
        }
        liveUser.onChange(scope, action)
    }

    override suspend fun currentUser() = liveUser.value ?: repo.loadLocalUser()?.takeIf {
        it.status == User.Status.SignedIn
    }
}