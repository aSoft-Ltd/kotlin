package tz.co.asoft.tools

import kotlinx.html.*
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onSubmitFunction
import org.w3c.dom.HTMLFormElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSelectElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventTarget
import org.w3c.files.File
import org.w3c.files.FileList

inline fun <T> T.onOptionChanged(crossinline handler: HTMLSelectElement.(Event) -> Unit) where T : SELECT, T : CommonAttributeGroupFacade {
    onChangeFunction = { e -> e.target?.to<HTMLSelectElement>()?.let { it.handler(e) } }
}

inline fun <T> T.onFileInputChanged(crossinline handler: HTMLInputElement.(FileList) -> Unit) where T : INPUT, T : CommonAttributeGroupFacade {
    onChangeFunction = { e ->
        e.target?.to<HTMLInputElement>()?.let {
            val files = it.files ?: return@let
            it.handler(files)
        }
    }
}

inline fun <T> T.onSubmitForm(crossinline handler: HTMLFormElement.() -> Unit) where T : FORM, T : CommonAttributeGroupFacade {
    onSubmitFunction = { e ->
        e.preventDefault()
        e.target?.to<HTMLFormElement>()?.let { it.handler() }
    }
}