package com.pedrorau.infrastructure.config

import com.pedrorau.application.EventService
import com.pedrorau.domain.repository.EventRepository
import com.pedrorau.infrastructure.persistence.InMemoryEventRepository
import org.koin.dsl.module

val appModule = module {
    // Repositorios
    single<EventRepository> { InMemoryEventRepository() }

    // Servicios
    single<EventService> { EventService(get()) }
}