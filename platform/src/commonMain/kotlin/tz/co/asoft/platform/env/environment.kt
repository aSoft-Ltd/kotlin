package tz.co.asoft.platform.env

import tz.co.asoft.platform.core.Ctx

@Deprecated("Use the individual platform to read the values")
expect fun Ctx.env(): Map<String, Any>