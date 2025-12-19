package com.example.volunteerapp.domain.model

data class CreateEventRequest(
    val eventTitle: String,
    val eventDescription: String,
    val datetimeStart: String,
    val datetimeEnd: String
)