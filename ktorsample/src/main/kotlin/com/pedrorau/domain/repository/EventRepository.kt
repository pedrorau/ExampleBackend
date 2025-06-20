package com.pedrorau.domain.repository

import com.pedrorau.domain.models.Event

interface EventRepository {

    suspend fun getAllEvents(): List<Event>

    suspend fun getEventById(id: String): Event?

    suspend fun saveEvent(event: Event): Event

    suspend fun updateEvent(event: Event): Event?

    suspend fun deleteEvent(id: String): Boolean
}