package com.example.volunteerapp.data.remote
import com.example.volunteerapp.domain.model.*

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface VolunteerService {

    @GET("api/VolunteersEvents/future-events")
    suspend fun getFutureEvents(
        @Header("Authorization") token: String
    ): List<EventResponse>

    @POST("api/VolunteersEvents/subscribe")
    suspend fun subscribeToEvent(
        @Header("Authorization") token: String,
        @Query("eventId") eventId: UInt
    ): Response<Unit>

    @GET("api/VolunteersEvents/closed-events")
    suspend fun getClosedEvents(
        @Header("Authorization") token: String
    ): List<VolunteerClosedEventResponse>

    @GET("api/VolunteersEvents/my-events")
    suspend fun getMyEvents(
        @Header("Authorization") token: String
    ): List<EventResponse>
}