package com.pedrorau.domain.models

import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate

@Serializable
data class Event(
    val id: String,
    val name: String,
    val description: String,
    val location: String,
    val organizerName: String,
    val category: String,
    val date: LocalDate,
    val capacity: Int,
    val attendeeCount: Int = 0,
    val imageUrl: String? = null,
    val tags: List<String> = emptyList(),
    val price: Double? = null,
    val isActive: Boolean = true
)