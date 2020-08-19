package tz.co.asoft

fun Map<String, *>.toOptions(): RestOptions {
    val url: String by this
    val version: String by this
    val headers: Map<String, String> by this
    return RestOptions(url, version, headers)
}