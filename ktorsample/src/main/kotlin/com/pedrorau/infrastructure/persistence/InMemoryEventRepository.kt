package com.pedrorau.infrastructure.persistence

import com.pedrorau.domain.models.Event
import com.pedrorau.domain.repository.EventRepository
import kotlinx.datetime.LocalDate
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class InMemoryEventRepository : EventRepository {

    private val events = ConcurrentHashMap<String, Event>()

    init {
        val sampleEvents = listOf(
            Event(
                id = "1",
                name = "Conferencia de Tecnología",
                description = "Una conferencia sobre las últimas tendencias en tecnología",
                location = "Centro de Convenciones",
                organizerName = "TechCorp",
                category = "Tecnología",
                date = LocalDate(2025, 5, 15),
                capacity = 500,
                attendeeCount = 250,
                imageUrl = "https://example.com/tech-conf.jpg",
                tags = listOf("tech", "innovation", "AI"),
                price = 99.99,
                isActive = true
            ),
            Event(
                id = "2",
                name = "Concierto de Rock",
                description = "Un concierto de las mejores bandas de rock",
                location = "Estadio Municipal",
                organizerName = "RockProd",
                category = "Música",
                date = LocalDate(2025, 6, 20),
                capacity = 2000,
                attendeeCount = 1200,
                imageUrl = "https://example.com/rock-concert.jpg",
                tags = listOf("music", "rock", "live"),
                price = 149.99,
                isActive = true
            ),
            Event(
                id = "3",
                name = "Maratón Benéfica",
                description = "Corre por una buena causa",
                location = "Parque Central",
                organizerName = "Fundación Ayuda",
                category = "Deportes",
                date = LocalDate(2025, 7, 10),
                capacity = 1000,
                attendeeCount = 800,
                imageUrl = "https://example.com/marathon.jpg",
                tags = listOf("sports", "running", "charity"),
                price = 25.0,
                isActive = true
            )
        )

        sampleEvents.forEach { events[it.id] = it }
    }

    override suspend fun getAllEvents(): List<Event> {
        return events.values.toList()
    }

    override suspend fun getEventById(id: String): Event? {
        return events[id]
    }

    override suspend fun saveEvent(event: Event): Event {
        val eventWithId = if (event.id.isBlank()) event.copy(id = UUID.randomUUID().toString()) else event
        events[eventWithId.id] = eventWithId
        return eventWithId
    }

    override suspend fun updateEvent(event: Event): Event? {
        return if (events.containsKey(event.id)) {
            events[event.id] = event
            event
        } else {
            null
        }
    }

    override suspend fun deleteEvent(id: String): Boolean {
        return events.remove(id) != null
    }
}