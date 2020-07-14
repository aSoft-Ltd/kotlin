package tz.co.asoft.tools

import org.w3c.dom.events.Event

fun Event.persist(): Unit = asDynamic().persist()