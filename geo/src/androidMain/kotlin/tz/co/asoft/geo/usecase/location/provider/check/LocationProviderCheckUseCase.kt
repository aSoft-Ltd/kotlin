package tz.co.asoft.geo.usecase.location.provider.check

import android.content.Context
import android.location.LocationManager

actual class LocationProviderCheckUseCase actual constructor(private val ctx: Context) : ILocationProviderCheckUseCase {
    override fun invoke(): String? {
        val locationManager = ctx.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return when {
            locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) -> LocationManager.GPS_PROVIDER
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) -> LocationManager.NETWORK_PROVIDER
            else -> null
        }
    }
}