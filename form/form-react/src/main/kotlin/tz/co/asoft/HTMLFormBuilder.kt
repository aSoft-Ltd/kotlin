package tz.co.asoft

import org.w3c.dom.HTMLFormElement

class HTMLFormBuilder {
    private var submitHandler: (HTMLFormElement.() -> Unit)? = null

    infix fun onSubmit(handler: HTMLFormElement.() -> Unit): HTMLFormBuilder {
        submitHandler = handler
        return this
    }

    fun executeSubmit(form: HTMLFormElement) = submitHandler?.invoke(form)
}