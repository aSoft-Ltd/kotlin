package tz.co.asoft.places

import tz.co.asoft.firebase.core.*
import tz.co.asoft.firebase.firestore.firestore
import tz.co.asoft.persist.di.dao
import tz.co.asoft.persist.di.repo
import tz.co.asoft.persist.di.single
import tz.co.asoft.places.data.dao.PlacesFirebaseDao
import tz.co.asoft.places.data.repo.PlacesRepo
import tz.co.asoft.places.data.viewmodel.TZPlacesViewModel
import tz.co.asoft.places.injection.config.firebase
import tz.co.asoft.platform.core.Ctx

object injection {
    private object config {
        fun firebase() = FirebaseOptionsBuilder().apply {
            apiKey = "AIzaSyAAG_21gH0J526JoNvsfQ86mqAwci4LUic"
            authDomain = "asoft-places.firebaseapp.com"
            databaseUrl = "https=//asoft-places.firebaseio.com"
            projectId = "asoft-places"
            storageBucket = "asoft-places.appspot.com"
            messagingSenderId = "934679534088"
        }.build()
    }

    private object firebase {
        private var app: FirebaseApp? = null

        private fun app(ctx: Ctx) = app ?: Firebase.initializeApp(ctx, firebase()).also {
            app = it
        }

        fun firestore(ctx: Ctx) = app(ctx).firestore()
    }

    private object dao {
        fun places(ctx: Ctx) = dao { PlacesFirebaseDao(firebase.firestore(ctx)) }
    }

    object repo {
        fun places(ctx: Ctx) = repo { PlacesRepo(dao.places(ctx)) }
    }

    object viewModel {
        fun places(ctx: Ctx) = TZPlacesViewModel(repo.places(ctx))
    }
}