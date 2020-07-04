package tz.co.asoft.rest.controller.ktor

import io.ktor.application.Application
import io.ktor.routing.Routing

interface IModule {
    fun setRoutes(app: Application): Routing
}