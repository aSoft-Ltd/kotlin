package tz.co.asoft.auth.usecase

import tz.co.asoft.auth.User
import tz.co.asoft.auth.repo.IUsersRepo
import tz.co.asoft.klock.DateTime
import tz.co.asoft.tools.emit
import tz.co.asoft.tools.flowTask

open class UpdateStatusUseCase(
    private val repo: IUsersRepo
) {
    operator fun invoke(u: User, s: User.Status) = flowTask<User> {
        emit(50)
        val user = u.copy(
            lastSeen = DateTime.nowUnixLong(),
            status = s
        )
        if (s == User.Status.SignedOut) {
            repo.deleteLocal()
        }
        emit(repo.remoteDao.edit(user))
    }
}