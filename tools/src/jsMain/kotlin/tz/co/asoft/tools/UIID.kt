package tz.co.asoft.tools

import org.w3c.dom.Element
import org.w3c.dom.HTMLElement
import kotlin.browser.document

class UIID(val value: String) {
    companion object {
        private val ids = mutableListOf<UIID>()

        fun getId(name: String = "view"): UIID {
            return if (!ids.map { it.value }.contains(name) && document.body?.find<Element>(By.id(name)) == null) {
                UIID(name)
            } else {
                val guess = "$name-id-${(1000000..9999999).random()}"
                if (document.body?.find<Element>(By.id(guess)) == null) {
                    UIID(guess)
                } else {
                    getId(name)
                }
            }
        }
    }

    init {
        ids.add(this)
    }

    fun release() {
        ids.remove(this)
    }
    
    fun <T : HTMLElement> get() = document.body?.find<T>(By.id(value))
        ?: throw Exception("Element with ID $value not found")
}