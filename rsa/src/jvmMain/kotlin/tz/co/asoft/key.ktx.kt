package tz.co.asoft

import java.security.Key
import javax.xml.bind.DatatypeConverter

fun Key.toBase64(): String = DatatypeConverter.printBase64Binary(encoded)