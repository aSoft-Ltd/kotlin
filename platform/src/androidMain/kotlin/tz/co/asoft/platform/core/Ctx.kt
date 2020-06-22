package tz.co.asoft.platform.core

import android.content.Context
import android.widget.Toast

@Deprecated("Use Android Context Instead")
actual typealias Ctx = Context

actual fun Context.alert(msg: Any?) {
    Toast.makeText(this, msg.toString(), Toast.LENGTH_LONG).show()
}