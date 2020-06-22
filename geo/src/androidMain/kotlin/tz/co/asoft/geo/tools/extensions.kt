package tz.co.asoft.geo.tools

import com.google.android.gms.maps.model.LatLng
import tz.co.asoft.geo.Cord

fun Cord.toLatLng() = LatLng(lat, lng)