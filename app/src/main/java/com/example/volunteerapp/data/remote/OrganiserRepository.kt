package com.example.volunteerapp.data.remote

import com.example.volunteerapp.domain.model.CreateEventRequest
import com.example.volunteerapp.domain.model.CreateEventResponse

class OrganiserRepository(
    private val api: OrganiserService,
) {

    suspend fun createEvent(
        request: CreateEventRequest
    ): Result<CreateEventResponse> {

        val token = SessionManager.getToken()
            ?: return Result.failure(Exception("No token"))

        return runCatching {
            api.createEvent(
                token = "Bearer $token",
                request = request
            )
        }
    }
}
