package tz.co.asoft.tools

import kotlinx.html.CommonAttributeGroupFacade
import org.w3c.dom.Element
import org.w3c.dom.HTMLFormElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventTarget
import org.w3c.files.File

val EventTarget?.value: String get() = asDynamic().value.toString()
val EventTarget?.files: Array<File> get() = asDynamic().files

fun <T : Element> EventTarget.to() = unsafeCast<T>()

fun Event.persist(): Unit = asDynamic().persist()

inline fun <T> Event.targetElement(): T where T : Element, T : CommonAttributeGroupFacade {
    return target!!.to()
}