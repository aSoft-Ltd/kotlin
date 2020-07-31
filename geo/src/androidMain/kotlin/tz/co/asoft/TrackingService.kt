package tz.co.asoft

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TrackingService(ctx: Context) : ITrackingServices, CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Default

    override var update_interval: Long = 1000 * 60 * 1 // 1 min
        set(value) {
            if (listener != null) {
                job.cancelChildren()
                startScheduler()
            }
            field = value
        }
    override var update_distance: Float = 10f

    private val locationManager = ctx.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private val trackHandlers = mutableSetOf<(Cord?) -> Unit>()

    private var listener: Listener? = null

    private val provider: String? = when {
        locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) -> LocationManager.GPS_PROVIDER
        locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) -> LocationManager.NETWORK_PROVIDER
        else -> null
    }

    private fun startScheduler(): Job = launch {
        delay(update_interval)
        dispatch()
        startScheduler()
    }

    @SuppressLint("MissingPermission")
    override fun onCordChanged(handler: (Cord?) -> Unit) {
        if (provider == null) {
            Log.e("asoft-geo/tracker", "Provider Not Found")
            return
        }
        Log.i("asoft-geo/tracker", "Provider Found")
        trackHandlers.add(handler)
        if (listener == null) {
            listener = Listener()
            launch(Dispatchers.Main) {
                locationManager.requestLocationUpdates(provider, update_interval, update_distance, listener)
                startScheduler()
            }
        }
        handler(getLocation())
    }

    @SuppressLint("MissingPermission")
    override fun getLocation() = locationManager.getLastKnownLocation(provider).toCord()

    override fun dispatch() {
        trackHandlers.forEach {
            launch(Dispatchers.Main) { it(getLocation()) }
        }
    }

    override fun stop() {
        job.cancel()
        trackHandlers.clear()
        listener?.let { locationManager.removeUpdates(it) }
        listener = null
    }

    private fun Location?.toCord(): Cord? = if (this != null) {
        Cord().apply {
            lat = latitude
            lng = longitude
        }
    } else null

    private inner class Listener : LocationListener {

        override fun onLocationChanged(location: Location?) {
            dispatch()
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

        }

        override fun onProviderEnabled(provider: String?) {

        }

        override fun onProviderDisabled(provider: String?) {

        }
    }
}