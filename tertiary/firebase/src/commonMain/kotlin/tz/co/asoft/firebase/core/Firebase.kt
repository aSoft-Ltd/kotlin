package tz.co.asoft.firebase.core

import tz.co.asoft.platform.core.Ctx

@Deprecated("Do not use this")
expect object Firebase {
    fun initializeApp(ctx: Ctx, options: FirebaseOptions, name: String? = null): FirebaseApp
}