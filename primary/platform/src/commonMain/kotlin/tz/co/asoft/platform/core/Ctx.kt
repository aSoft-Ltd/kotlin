package tz.co.asoft.platform.core

@Deprecated("Do not use Context in common code")
expect abstract class Ctx

expect fun Ctx.alert(msg: Any?)