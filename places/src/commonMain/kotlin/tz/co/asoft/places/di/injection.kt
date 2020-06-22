package tz.co.asoft.places.di

import kotlinx.atomicfu.atomic
import tz.co.asoft.firebase.core.*
import tz.co.asoft.firebase.firestore.firestore
import tz.co.asoft.persist.di.dao
import tz.co.asoft.persist.di.repo
import tz.co.asoft.persist.tools.Cause
import tz.co.asoft.places.data.dao.PlacesFirebaseDao
import tz.co.asoft.places.data.repo.PlacesRepo
import tz.co.asoft.places.data.viewmodel.TZPlacesViewModel
import tz.co.asoft.platform.core.Ctx

object injection {
    val firebaseOptions = FirebaseOptionsBuilder().apply {
        apiKey = "AIzaSyAAG_21gH0J526JoNvsfQ86mqAwci4LUic"
        authDomain = "asoft-places.firebaseapp.com"
        databaseUrl = "https=//asoft-places.firebaseio.com"
        projectId = "asoft-places"
        storageBucket = "asoft-places.appspot.com"
        messagingSenderId = "934679534088"
    }.build()

    private val app = atomic<FirebaseApp?>(null)

    fun init(a: FirebaseApp) {
        app.value = a
    }

    fun app(): FirebaseApp = app.value
        ?: throw Cause("Make sure you called Places.Init while the app has started")

    private object dao {
        fun places() = dao { PlacesFirebaseDao(app().firestore()) }
    }

    object repo {
        fun places() = repo { PlacesRepo(dao.places()) }
    }

    object viewModel {
        fun places() = TZPlacesViewModel(repo.places())
    }
}