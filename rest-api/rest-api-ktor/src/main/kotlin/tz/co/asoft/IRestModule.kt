package tz.co.asoft

import io.ktor.application.Application
import io.ktor.routing.Routing

interface IRestModule {
    val version: String
    val root: String
    val subRoot: String?
    val path get() = "/$version/$root" + if (subRoot != null) "/$subRoot" else ""
    fun setRoutes(app: Application, log: Logger): Routing
}