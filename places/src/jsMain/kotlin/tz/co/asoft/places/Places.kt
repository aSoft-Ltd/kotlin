package tz.co.asoft.places

import tz.co.asoft.firebase.core.Firebase
import tz.co.asoft.places.di.injection
import tz.co.asoft.places.di.injection.firebaseOptions

object Places {
    fun init() {
        injection.init(Firebase.initializeApp(firebaseOptions, "asoft-places"))
    }
}