package com.pedrorau.application

import com.pedrorau.domain.models.Event
import com.pedrorau.domain.repository.EventRepository

class EventService(private val eventRepository: EventRepository) {

    suspend fun getAllEvents(): List<Event> {
        return eventRepository.getAllEvents().filter { it.isActive }
    }

    suspend fun getEventById(id: String): Event? {
        val event = eventRepository.getEventById(id)
        return if (event != null && event.isActive) event else null
    }

    suspend fun createEvent(event: Event): Event {
        return eventRepository.saveEvent(event)
    }

    suspend fun updateEvent(event: Event): Event? {
        return eventRepository.updateEvent(event)
    }

    suspend fun deleteEvent(id: String): Boolean {
        val event = eventRepository.getEventById(id) ?: return false
        val updatedEvent = event.copy(isActive = false)
        return eventRepository.updateEvent(updatedEvent) != null
    }
}