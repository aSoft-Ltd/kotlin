package tz.co.asoft.auth.viewmodel

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tz.co.asoft.auth.viewmodel.LoginFormViewModel.State
import tz.co.asoft.auth.usecase.CreateAdminUseCase
import tz.co.asoft.auth.usecase.SignInUseCase
import tz.co.asoft.tools.Task
import tz.co.asoft.viewmodel.VModel

class LoginFormViewModel(
    private val createAdminUC: CreateAdminUseCase,
    private val signInUC: SignInUseCase
) : VModel<LoginFormViewModel.Intent, State>(State.ShowForm) {

    sealed class State {
        class Loading(val msg: String) : State()
        object ShowForm : State()
        class Error(val msg: String) : State()
    }

    sealed class Intent {
        object CreateAdmin : Intent()
        class SignIn(val email: String, val pwd: String) : Intent()
    }

    override fun post(i: Intent): Any = when (i) {
        is Intent.CreateAdmin -> launch { createAdminUC() }
        is Intent.SignIn -> signIn(i)
    }

    private fun signIn(i: Intent.SignIn) = launch {
        ui.value = State.Loading("Signing you in")
        signInUC(i.email, i.pwd).collect {
            ui.value = when (it) {
                is Task.Progress -> State.Loading("${it.pct}% ${it.msg}")
                is Task.Failed -> State.Error(it.cause.message ?: "Unknown error")
                is Task.Completed -> State.Loading("100%, Setting up your screen")
            }
        }
    }
}
