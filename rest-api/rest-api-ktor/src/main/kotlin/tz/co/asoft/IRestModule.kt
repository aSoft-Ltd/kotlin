package tz.co.asoft

import io.ktor.application.Application
import io.ktor.routing.Routing

interface IRestModule {
    fun setRoutes(app: Application): Routing
}