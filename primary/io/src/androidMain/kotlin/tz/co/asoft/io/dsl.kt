package tz.co.asoft.io

import android.net.Uri
import java.net.URI
import java.net.URLEncoder

fun java.io.File.toFile() = File(this)
fun Uri.toURI() = URI(URLEncoder.encode(toString(), "UTF-8"))