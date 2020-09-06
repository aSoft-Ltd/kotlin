package tz.co.asoft

import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.header
import io.ktor.client.request.post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class RestAppender(val options: RestAppenderOptions) : Appender, CoroutineScope by CoroutineScope(SupervisorJob()) {
    override fun append(level: LogLevel, msg: String, vararg data: Pair<String, Any?>) {
        if (level >= options.level) launch {
            val log = formBuilder {
                append("message", msg)
                append("level", level.name)
                append("time", DateTime.nowUnixLong().asFormatedDate())
                data.forEach {
                    append(key = it.first, value = it.second.toString())
                }
            }

            options.client.post<String>(options.url) {
                options.headers?.forEach { header(it.key, it.value) }
                body = MultiPartFormDataContent(log)
            }
        }
    }

    override fun append(vararg o: Any?) = append(level = LogLevel.DEBUG, msg = o.toString())
}