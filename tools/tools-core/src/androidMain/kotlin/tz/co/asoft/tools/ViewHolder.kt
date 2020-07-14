package tz.co.asoft.tools

import android.view.View
import kotlin.reflect.KProperty

open class ViewHolder(protected val view: View?) {

    fun Id(id: Int) = Delegate(id)

    inner class Delegate(private val id: Int) {
        operator fun <T : View> getValue(thisRef: Any?, prop: KProperty<*>): T {
            return view?.findViewById(id) ?: throw Exception("View ${prop.name} not found")
        }
    }
}