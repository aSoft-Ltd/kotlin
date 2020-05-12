package tz.co.asoft.auth.exceptions

import tz.co.asoft.auth.UserAccount
import tz.co.asoft.email.Email
import tz.co.asoft.persist.tools.Cause
import tz.co.asoft.phone.Phone

sealed class AuthException(message: String) : Cause(message)

class UserAccountExistException(val ua: UserAccount) : AuthException(
    "UserAccount {name: ${ua.name}, uid: ${ua.uid}} already exist"
)

class EmailExistException(val email: Email) : AuthException(
    "Email ${email.value} already exist"
)

class PhoneExistException(val phone: Phone) : AuthException(
    "Phone ${phone.value} already exist"
)