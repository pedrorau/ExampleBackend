package com.pedrorau.infrastructure.api

import com.pedrorau.application.EventService
import com.pedrorau.infrastructure.api.dto.ErrorResponse
import com.pedrorau.infrastructure.api.dto.EventListResponse
import com.pedrorau.infrastructure.api.dto.EventSummaryDto
import com.pedrorau.infrastructure.api.dto.EventDetailDto
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import org.koin.java.KoinJavaComponent.inject

fun Route.eventRoutes() {

    val eventService by inject<EventService>(EventService::class.java)

    /**
     * GET /api/events
     * Devuelve una lista de todos los eventos activos
     */
    get("/events") {
        val events = eventService.getAllEvents()
        val response = EventListResponse(
            events = events.map { EventSummaryDto.fromDomain(it) },
            count = events.size
        )
        call.respond(response)
    }

    /**
     * GET /api/events/{id}
     * Devuelve un evento específico por su ID
     */
    get("/events/{id}") {
        val id = call.parameters["id"] ?: return@get call.respond(
            HttpStatusCode.BadRequest,
            ErrorResponse("ID del evento no proporcionado")
        )

        val event = eventService.getEventById(id)
        if (event != null) {
            call.respond(EventDetailDto.fromDomain(event))
        } else {
            call.respond(
                HttpStatusCode.NotFound,
                ErrorResponse("Evento no encontrado con ID: $id")
            )
        }
    }
}