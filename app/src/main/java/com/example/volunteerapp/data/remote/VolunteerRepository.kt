package com.example.volunteerapp.data.remote

import com.example.volunteerapp.domain.model.EventResponse

class VolunteerRepository(
    private val api: VolunteerService
) {

    suspend fun getFutureEvents(): Result<List<EventResponse>> {
        val token = SessionManager.getToken()
            ?: return Result.failure(Exception("No token"))
        return runCatching { api.getFutureEvents("Bearer $token") }
    }

    suspend fun getMyEvents(): Result<List<EventResponse>> {
        val token = SessionManager.getToken()
            ?: return Result.failure(Exception("No token"))
        return runCatching { api.getMyEvents("Bearer $token") }
    }
    suspend fun getClosedEvents(): Result<List<EventResponse>> {
        val token = SessionManager.getToken()
            ?: return Result.failure(Exception("No token"))
        return runCatching { api.getClosedEvents("Bearer $token") }
    }


    suspend fun subscribe(eventId: UInt): Result<Unit> {
        val token = SessionManager.getToken()
            ?: return Result.failure(Exception("No token"))
        return runCatching {
            api.subscribeToEvent("Bearer $token", eventId)
        }
    }

}
