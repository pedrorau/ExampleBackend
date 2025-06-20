package com.pedrorau

import com.pedrorau.infrastructure.config.appModule
import com.pedrorau.infrastructure.config.configureCors
import com.pedrorau.infrastructure.config.configureRouting
import com.pedrorau.infrastructure.config.configureSerialization
import com.pedrorau.infrastructure.config.configureMonitoring
import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain
import org.koin.core.context.GlobalContext.startKoin
import org.koin.logger.slf4jLogger
import org.koin.core.logger.Level

fun main(args: Array<String>) {
    startKoin {
        slf4jLogger(
            level = Level.INFO
        )
        modules(appModule)
    }
    EngineMain.main(args)
}

fun Application.module() {
    configureCors()
    configureMonitoring()
    configureSerialization()
    configureRouting()
}
