package tz.co.asoft.ui.react.tools

import org.w3c.dom.events.EventTarget
import org.w3c.files.File

val EventTarget?.value: String get() = asDynamic().value.toString()
val EventTarget?.files: Array<File> get() = asDynamic().files