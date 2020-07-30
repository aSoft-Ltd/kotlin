package tz.co.asoft

import kotlinx.html.FORM
import react.RBuilder
import styled.StyledDOMBuilder
import styled.styledForm

fun RBuilder.Form(builder: StyledDOMBuilder<FORM>.(Theme) -> Unit): FormBuilder {
    val fb = FormBuilder()
    ThemeConsumer { theme ->
        styledForm {
            attrs.onSubmitForm { fb.executeSubmit(this) }
            builder(theme)
        }
    }
    return fb
}