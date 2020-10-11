package tz.co.asoft

import java.security.Key

fun Key.toBase64(): String = Base64.encode(encoded)