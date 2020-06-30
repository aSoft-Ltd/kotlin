package tz.co.asoft.auth.usecase

import tz.co.asoft.auth.User
import tz.co.asoft.auth.repo.IUsersRepo
import tz.co.asoft.io.File
import tz.co.asoft.tools.emit
import tz.co.asoft.tools.flowTask

open class UploadPhotoUseCase(
    private val repo: IUsersRepo,
    private val userStateUC: UserStateUseCase
) {
    operator fun invoke(u: User, file: File) = flowTask<User> {
        emit(10, "Uploading File")
        val fileRef = repo.uploadPhoto(u, file)
        emit(60, "Saving to database")
        val user = repo.edit(u.copy(photoUrl = fileRef.url))
        emit(100, "Completed")
        userStateUC.live.value = UserStateUseCase.State.LoggedIn(user)
        emit(user)
    }
}