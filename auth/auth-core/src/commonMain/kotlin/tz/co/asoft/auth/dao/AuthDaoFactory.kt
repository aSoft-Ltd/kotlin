package tz.co.asoft.auth.dao

import tz.co.asoft.auth.UserAccount
import tz.co.asoft.persist.dao.IDao

abstract class AuthDaoFactory {
    abstract fun users(): IUsersDao
    abstract fun usersLocal(): IUsersLocalDao
    abstract fun userAccounts(): IDao<UserAccount>
}