package tz.co.asoft

import io.ktor.client.request.forms.formData
import io.ktor.http.content.PartData

inline fun formData(block: HttpFormBuilder.() -> Unit): List<PartData> =
        formData(*HttpFormBuilder().apply(block).build().toTypedArray())