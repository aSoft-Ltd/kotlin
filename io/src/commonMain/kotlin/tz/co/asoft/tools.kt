package tz.co.asoft

internal fun String.extension() = split(".").last()

internal fun String.extensionOrNull() = split(".").lastOrNull()

internal fun String.nameWithoutExtension() =
    (split(".") - listOfNotNull(extensionOrNull())).joinToString(".")
