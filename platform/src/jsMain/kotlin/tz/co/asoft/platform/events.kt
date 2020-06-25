package tz.co.asoft.platform

import org.w3c.dom.events.Event

fun Event.persist() :Unit = asDynamic().persist()
