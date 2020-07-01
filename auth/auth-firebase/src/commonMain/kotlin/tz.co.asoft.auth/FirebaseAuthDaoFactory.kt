package tz.co.asoft.auth

import tz.co.asoft.auth.dao.AuthDaoFactory
import tz.co.asoft.auth.dao.UsersLocalDao
import tz.co.asoft.firebase.core.FirebaseApp
import tz.co.asoft.firebase.firestore.firestore
import tz.co.asoft.firebase.storage.storage
import tz.co.asoft.persist.di.dao
import tz.co.asoft.storage.IStorage

class FirebaseAuthDaoFactory(
    private val app: FirebaseApp,
    private val db: IStorage
) : AuthDaoFactory() {
    override fun users() = dao { UsersFirebaseDao(app.firestore(), app.storage()) }

    override fun usersLocal() = dao { UsersLocalDao(db) }

    override fun userAccounts() = dao { UserAccountsFirebaseDao(app.firestore()) }
}