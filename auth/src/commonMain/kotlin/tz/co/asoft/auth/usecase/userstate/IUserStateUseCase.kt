package tz.co.asoft.auth.usecase.userstate

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import tz.co.asoft.auth.User
import tz.co.asoft.rx.LiveData

interface IUserStateUseCase {
    val liveUser: LiveData<User?>
    val live: MutableStateFlow<User?>
    suspend fun currentUser(): User?

    @Deprecated("Try collecting on the live member")
    fun onChange(scope: CoroutineScope, action: (User?) -> Unit)
}