package tz.co.asoft.auth.usecase.createadmin

import tz.co.asoft.auth.User
import tz.co.asoft.auth.UserAccount
import tz.co.asoft.auth.usecase.registeruser.IRegisterUserUseCase
import tz.co.asoft.persist.result.catching

open class CreateAdminUseCase(private val registerUserUC: IRegisterUserUseCase) :
    ICreateAdminUseCase {

    private val admin = User().apply {
        id = 0L
        uid = "administrator"
        name = "System Admin"
        emails.add("admin@admin.com")
        phones.add("255000000000")
        username = "administrator"
        password = "adminadmin"
        permits.apply {
            add(":dev")
            add(":admin")
        }
    }

    private val ua = UserAccount().apply {
        id = 0L
        uid = "System Administrator"
        name = "System Administrator"
        permits.add(":admin")
    }

    override suspend fun invoke() = registerUserUC(admin, ua)
}