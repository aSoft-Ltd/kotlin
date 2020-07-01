package tz.co.asoft.auth

import tz.co.asoft.firebase.firestore.FirebaseDao
import tz.co.asoft.firebase.firestore.FirebaseFirestore

class UserAccountsFirebaseDao(firestore: FirebaseFirestore) : FirebaseDao<UserAccount>(
    firestore, "user-accounts", UserAccount.serializer()
)