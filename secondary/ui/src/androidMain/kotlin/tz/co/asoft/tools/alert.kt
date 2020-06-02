package tz.co.asoft.tools

import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tz.co.asoft.platform.core.Ctx

actual fun Ctx.alert(msg: Any?) {
    GlobalScope.launch(Dispatchers.Main) {
        Toast.makeText(this@alert, msg.toString(), Toast.LENGTH_LONG).show()
    }
}