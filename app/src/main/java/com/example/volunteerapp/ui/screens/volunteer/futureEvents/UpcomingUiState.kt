package com.example.volunteerapp.ui.screens.volunteer.futureEvents

import com.example.volunteerapp.domain.model.EventResponse

sealed class UpcomingUiState {
    object Loading : UpcomingUiState()
    data class Success(val events: List<EventResponse>) : UpcomingUiState()
    data class Error(val message: String) : UpcomingUiState()
}