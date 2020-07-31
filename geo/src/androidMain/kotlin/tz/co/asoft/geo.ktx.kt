@file:JvmName("GeoAndroid")

package tz.co.asoft

import com.google.android.gms.maps.model.LatLng
import tz.co.asoft.Cord

fun Cord.toLatLng() = LatLng(lat, lng)