package tz.co.asoft.auth

import tz.co.asoft.auth.dao.AuthDaoFactory
import tz.co.asoft.auth.di.injection

object Auth {
    fun init(dao: AuthDaoFactory) {
        injection.config(dao)
    }
}