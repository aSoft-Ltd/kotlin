package tz.co.asoft.auth.di

import tz.co.asoft.auth.di.injection.useCase
import tz.co.asoft.auth.viewmodel.LoginFormViewModel

object uinjection {
    object viewModel {
        fun loginForm() = LoginFormViewModel(
            createAdminUC = useCase.createAdmin(),
            signInUC = useCase.signIn()
        )
    }
}