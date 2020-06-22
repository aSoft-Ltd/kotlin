package tz.co.asoft.auth.usecase.createadmin

import tz.co.asoft.auth.User
import tz.co.asoft.auth.UserAccount
import tz.co.asoft.auth.usecase.registeruser.IRegisterUserUseCase
import tz.co.asoft.email.Email
import tz.co.asoft.persist.result.catching
import tz.co.asoft.phone.Phone

open class CreateAdminUseCase(private val registerUserUC: IRegisterUserUseCase) :
    ICreateAdminUseCase {

    private val adminUID = "administrator"
    private val admin = User(
        id = 0L,
        uid = adminUID,
        name = "System Admin",
        emails = listOf(Email("admin@admin.com").value),
        phones = listOf(Phone("255000000000").value),
        username = "administrator",
        password = "adminadmin",
        permits = listOf(":dev", ":admin")
    )

    private val ua = UserAccount(
        id = 0L,
        uid = adminUID,
        name = "System Administrator",
        permits = listOf(":admin")
    )

    override suspend fun invoke() = registerUserUC(admin, ua)
}