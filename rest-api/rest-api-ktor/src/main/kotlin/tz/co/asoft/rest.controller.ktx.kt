package tz.co.asoft

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.util.pipeline.PipelineContext
import kotlinx.serialization.KSerializer
import java.util.logging.Logger

suspend fun <T> PipelineContext<Unit, ApplicationCall>.send(
    serializer: KSerializer<T>,
    res: Result<T>
) = call.respondText(Result.stringify(serializer, res), ContentType.Application.Json).also {
    val log = Logger("Rest Logger")
    when (res) {
        is Result.Success -> when (val data = res.data) {
            is Entity -> log.i("Sent entity with uid: ${data.uid} successfully")
            is Collection<*> -> log.i("Sent ${data.size} entities successfully")
            else -> log.i("Request sent successfully")
        }
        is Result.Failure -> log.w("Sending error msg: ${res.msg}")
    }
}