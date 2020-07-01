package tz.co.asoft.auth

import tz.co.asoft.firebase.firestore.FirebaseDao
import tz.co.asoft.firebase.firestore.FirebaseFirestore

class RolesFirebaseDao(firestore: FirebaseFirestore) : FirebaseDao<Role>(
    firestore, "roles", Role.serializer()
)