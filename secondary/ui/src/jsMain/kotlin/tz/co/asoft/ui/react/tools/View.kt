package tz.co.asoft.ui.react.tools

import kotlinext.js.jsObject
import kotlinx.html.InputType
import org.w3c.dom.Element
import kotlin.browser.document

object View {
    fun getId(name: String = "view"): String {
        val id = "$name-id-${(1000000..9999999).random()}"
        return if (byId<Element>(id) == null) {
            id
        } else {
            getId(name)
        }
    }

    fun <T : Element> byId(id: String): T? = document.getElementById(id).unsafeCast<T?>()
}

external interface FormTextInput {
    var name: String
    var type: InputType
    var hint: String
    var required: Boolean
    var value: String
}

fun field(builder: FormTextInput.() -> Unit) = jsObject<FormTextInput> {
    required = true
    type = InputType.text
    builder()
}