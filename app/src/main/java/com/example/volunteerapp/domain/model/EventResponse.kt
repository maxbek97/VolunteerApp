package com.example.volunteerapp.domain.model

data class EventResponse(
    val id: UInt,
    val eventTitle: String,
    val eventDescription: String,
    val datetimeStart: String,
    val datetimeEnd: String,
    val creationDate: String
)