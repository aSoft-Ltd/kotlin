package tz.co.asoft.auth.di

import tz.co.asoft.auth.dao.AuthDaoFactory
import tz.co.asoft.auth.repo.UsersRepo
import tz.co.asoft.auth.usecase.*
import tz.co.asoft.persist.di.DaoFactoryConfig
import tz.co.asoft.persist.di.*
import tz.co.asoft.persist.repo.CachedRepo

object injection : DaoFactoryConfig<AuthDaoFactory>() {
    object repo {
        fun users() = repo { UsersRepo(dao.users(), dao.usersLocal()) }
        fun userAccounts() = repo { CachedRepo(dao.userAccounts()) }
    }

    object useCase {
        fun createAdmin() = CreateAdminUseCase(registerUser())

        fun userState() = single { UserStateUseCase(repo.users()) }

        fun updateStatus() = UpdateStatusUseCase(repo.users())
        
        fun signIn() = SignInUseCase(repo.users(), userState(), updateStatus())

        fun signOut() = SignOutUseCase(repo.users(), userState(), updateStatus())

        fun registerUser() = RegisterUserUseCase(repo.users(), repo.userAccounts())

        fun registerAndSignIn() = RegisterUserAndSignInUseCase(registerUser(), signIn())

        fun uploadPhoto() = UploadPhotoUseCase(repo.users(), userState())

        fun deleteUser() = DeleteUserUseCase(repo.users())
    }
}