package com.pedrorau.infrastructure.config

import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.application.log
import io.ktor.server.plugins.cors.routing.CORS

fun Application.configureCors() {
    install(CORS) {
        anyHost()

        // Dominios específicos (para producción)
        // allowHost("tudominio.com", schemes = listOf("https"))
        // allowHost("*.tudominio.com", schemes = listOf("https"))

        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)

        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Accept)
        allowHeader("X-Requested-With")

        allowCredentials = true

        maxAgeInSeconds = 3600

        // allowSameOrigin = true

        exposeHeader(HttpHeaders.ContentLength)
        exposeHeader(HttpHeaders.ContentType)
    }

    log.info("CORS configurado correctamente")
}