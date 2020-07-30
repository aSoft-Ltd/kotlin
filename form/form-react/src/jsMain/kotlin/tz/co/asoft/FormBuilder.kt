package tz.co.asoft

import org.w3c.dom.HTMLFormElement

class FormBuilder {
    private var submitHandler: (HTMLFormElement.() -> Unit)? = null

    infix fun onSubmit(handler: HTMLFormElement.() -> Unit): FormBuilder {
        submitHandler = handler
        return this
    }

    fun executeSubmit(form: HTMLFormElement) = submitHandler?.invoke(form)
}