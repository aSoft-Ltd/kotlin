package tz.co.asoft.geo.usecase.location.provider.request

import android.content.Context
import android.content.Intent
import android.provider.Settings


actual class LocationProviderRequestUseCase actual constructor(private val ctx: Context) : ILocationProviderRequestUseCase {
    override fun invoke() {
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        ctx.startActivity(intent)
    }
}