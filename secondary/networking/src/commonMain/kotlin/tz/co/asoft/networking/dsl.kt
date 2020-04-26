package tz.co.asoft.networking

import io.ktor.client.request.forms.formData
import io.ktor.http.content.PartData

inline fun formData(block: FormBuilder.() -> Unit): List<PartData> =
        formData(*FormBuilder().apply(block).build().toTypedArray())