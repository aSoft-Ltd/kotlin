package tz.co.asoft

import android.net.Uri
import java.net.URI
import java.net.URLEncoder

fun Uri.toURI() = URI(URLEncoder.encode(toString(), "UTF-8"))