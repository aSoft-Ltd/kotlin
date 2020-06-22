package tz.co.asoft.firebase.firestore

import tz.co.asoft.auth.UserAccount

class UserAccountsDao(firestore: FirebaseFirestore) :
    FirebaseDao<UserAccount>(firestore, "user-accounts", UserAccount.serializer())