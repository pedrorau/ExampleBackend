package com.pedrorau.infrastructure.config

import com.pedrorau.infrastructure.api.eventRoutes
import io.ktor.server.application.Application
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/test-cors") {
            call.respondText("CORS Test Response")
        }

        route("/api") {
            eventRoutes()
        }
    }
}
