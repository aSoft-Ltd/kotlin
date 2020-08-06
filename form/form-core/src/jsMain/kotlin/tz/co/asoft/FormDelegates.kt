package tz.co.asoft

import org.w3c.dom.HTMLFormElement
import org.w3c.dom.HTMLInputElement
import org.w3c.files.File
import org.w3c.files.get
import kotlin.js.Date
import kotlin.reflect.KProperty

object FormDelegates {
    fun <T> error(property: KProperty<*>): T {
        throw Exception("${property.name} is not found in form")
    }

    class Multi(private val form: HTMLFormElement) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): List<String> {
            return form.findAll<HTMLInputElement>(By.name(property.name)).filter { it.checked }.map { it.value }
        }
    }

    class Texts(private val form: HTMLFormElement, private val default: String?) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
            return form[property.name] ?: default ?: error(property)
        }
    }

    class Integers(private val form: HTMLFormElement, private val default: Int?) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): Int {
            return form[property.name]?.toInt() ?: default ?: error(property)
        }
    }

    class Longs(private val form: HTMLFormElement, private val default: Long?) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): Long {
            return form[property.name]?.toLong() ?: default ?: error(property)
        }
    }

    class Doubles(private val form: HTMLFormElement, private val default: Double?) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): Double {
            return form[property.name]?.toDouble() ?: default ?: error(property)
        }
    }

    class Dates(private val form: HTMLFormElement, private val default: Date?) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): Date {
            return form[property.name]?.let { Date(it) } ?: default ?: error(property)
        }
    }

    class Files(private val form: HTMLFormElement) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): File {
            val name = property.name
            return form.find<HTMLInputElement>(By.name(name)).files?.get(0)
                ?: throw Exception("Can't get file from input element with name $name")
        }
    }

    class DateLong(private val form: HTMLFormElement, private val default: Long?) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): Long {
            return form[property.name]?.let { Date(it) }?.getTime()?.toLong() ?: default
            ?: error(property)
        }
    }
}

fun HTMLFormElement.multi() = FormDelegates.Multi(this)
fun HTMLFormElement.text(default: String? = null) = FormDelegates.Texts(this, default)
fun HTMLFormElement.int(default: Int? = null) = FormDelegates.Integers(this, default)
fun HTMLFormElement.double(default: Double? = null) = FormDelegates.Doubles(this, default)
fun HTMLFormElement.long(default: Long? = null) = FormDelegates.Longs(this, default)
fun HTMLFormElement.date(default: Date? = null) = FormDelegates.Dates(this, default)
fun HTMLFormElement.dateLong(default: Long? = null) = FormDelegates.DateLong(this, default)
fun HTMLFormElement.file() = FormDelegates.Files(this)