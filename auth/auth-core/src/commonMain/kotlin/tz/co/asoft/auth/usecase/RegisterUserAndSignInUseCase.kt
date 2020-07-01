package tz.co.asoft.auth.usecase

import kotlinx.coroutines.flow.emitAll
import tz.co.asoft.auth.User
import tz.co.asoft.auth.UserAccount
import tz.co.asoft.tools.flowTask
import tz.co.asoft.tools.map
import tz.co.asoft.tools.mapProgress

open class RegisterUserAndSignInUseCase(
    private val registerUserUC: RegisterUserUseCase,
    private val signInUC: SignInUseCase
) {
    operator fun invoke(user: User, ua: UserAccount?) = flowTask<User> {
        val pwd = user.password
        emitAll(registerUserUC(user, ua).mapProgress(0..80))
        emitAll(signInUC(user.emails.first(), pwd).map(80, 100))
    }
}