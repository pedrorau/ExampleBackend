package com.pedrorau.infrastructure.api.dto

import com.pedrorau.domain.models.Event
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class EventListResponse(
    val events: List<EventSummaryDto>,
    val count: Int
)

@Serializable
data class EventSummaryDto(
    val id: String,
    val name: String,
    val category: String,
    val date: LocalDate,
    val location: String,
    val imageUrl: String? = null
) {
    companion object {
        fun fromDomain(event: Event): EventSummaryDto {
            return EventSummaryDto(
                id = event.id,
                name = event.name,
                category = event.category,
                date = event.date,
                location = event.location,
                imageUrl = event.imageUrl
            )
        }
    }
}

@Serializable
data class EventDetailDto(
    val id: String,
    val name: String,
    val description: String,
    val location: String,
    val organizerName: String,
    val category: String,
    val date: LocalDate,
    val capacity: Int,
    val attendeeCount: Int,
    val imageUrl: String? = null,
    val tags: List<String> = emptyList(),
    val price: Double? = null
) {
    companion object {
        fun fromDomain(event: Event): EventDetailDto {
            return EventDetailDto(
                id = event.id,
                name = event.name,
                description = event.description,
                location = event.location,
                organizerName = event.organizerName,
                category = event.category,
                date = event.date,
                capacity = event.capacity,
                attendeeCount = event.attendeeCount,
                imageUrl = event.imageUrl,
                tags = event.tags,
                price = event.price
            )
        }
    }
}

@Serializable
data class ErrorResponse(
    val message: String
)