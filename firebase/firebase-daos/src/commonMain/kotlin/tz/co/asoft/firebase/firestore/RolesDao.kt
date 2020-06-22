package tz.co.asoft.firebase.firestore

import tz.co.asoft.auth.Role
import tz.co.asoft.firebase.firestore.FirebaseFirestore

class RolesDao(firestore: FirebaseFirestore) : FirebaseDao<Role>(firestore, "roles", Role.serializer())