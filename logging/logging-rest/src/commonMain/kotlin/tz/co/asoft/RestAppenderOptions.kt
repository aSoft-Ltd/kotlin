package tz.co.asoft

import io.ktor.client.HttpClient

data class RestAppenderOptions(
    val url: String,
    val level: LogLevel = LogLevel.DEBUG,
    val headers: Map<String,String>?=null,
    val client: HttpClient = HttpClient { }
)