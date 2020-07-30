package tz.co.asoft

import org.w3c.dom.events.Event

fun Event.persist(): Unit = asDynamic().persist()