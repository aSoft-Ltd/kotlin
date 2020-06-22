package tz.co.asoft.places

import android.content.Context
import com.google.firebase.FirebaseApp
import tz.co.asoft.places.di.injection

object Places {
    fun init(ctx: Context) {
        injection.init(FirebaseApp.initializeApp(ctx, injection.firebaseOptions, "asoft-places"))
    }
}