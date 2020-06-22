package tz.co.asoft.geo.usecase.location.lastknown

import android.annotation.SuppressLint
import android.content.Context
import android.location.Criteria
import android.location.LocationManager
import tz.co.asoft.geo.Cord
import tz.co.asoft.platform.core.usecase.permission.check.IPermissionCheckUseCase

actual class LastKnownLocationUseCase actual constructor(
        private val ctx: Context,
        private val locationPermissionCheckUC: IPermissionCheckUseCase
) : ILastKnownLocationUseCase {
    @SuppressLint("MissingPermission")
    override fun invoke(): Cord? {
        val lm = ctx.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val criteria = Criteria()
        return if (locationPermissionCheckUC.isPermitted()) {
            val loc = lm.getLastKnownLocation(lm.getBestProvider(criteria, false)) ?: return null
            Cord(loc.latitude, loc.longitude, loc.altitude)
        } else null
    }
}