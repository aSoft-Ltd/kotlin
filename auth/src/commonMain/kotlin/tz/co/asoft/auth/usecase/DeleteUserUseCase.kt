package tz.co.asoft.auth.usecase

import tz.co.asoft.auth.User
import tz.co.asoft.auth.repo.IUsersRepo
import tz.co.asoft.email.Email
import tz.co.asoft.persist.tools.Cause
import tz.co.asoft.tools.emit
import tz.co.asoft.tools.flowTask

class DeleteUserUseCase(private val repo: IUsersRepo) {
    operator fun invoke(email: String, pwd: String) = flowTask<User> {
        emit(30, "Deleting user")
        val user = repo.load(Email(email), pwd) ?: throw Cause("User not found")
        emit(60)
        emit(repo.delete(user))
    }
}