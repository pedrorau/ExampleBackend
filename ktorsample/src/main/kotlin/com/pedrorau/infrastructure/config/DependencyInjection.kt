package com.pedrorau.infrastructure.config

import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.core.context.startKoin
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureDependencyInjection() {
    startKoin {
        slf4jLogger()
        modules(appModule)
    }
    install(Koin) {
        /*slf4jLogger()
        modules(
            appModule
        )*/
    }
}