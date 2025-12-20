package com.example.volunteerapp.data.remote

import com.example.volunteerapp.domain.model.CreateEventRequest
import com.example.volunteerapp.domain.model.CreateEventResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OrganiserService {

    @POST("api/OrganisersEvents/create-event")
    suspend fun createEvent(
        @Header("Authorization") token: String,
        @Body request: CreateEventRequest
    ): CreateEventResponse
}

