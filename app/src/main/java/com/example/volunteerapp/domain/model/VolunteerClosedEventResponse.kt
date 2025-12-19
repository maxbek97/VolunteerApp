package com.example.volunteerapp.domain.model

data class VolunteerClosedEventResponse(
    val id: UInt,
    val eventTitle: String,
    val eventDescription: String,
    val datetimeStart: String,
    val datetimeEnd: String,
    val creationDate: String,
    val attendance: String,
    val duracity: Int
)