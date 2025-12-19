package com.example.volunteerapp.ui.screens.volunteer.closedEvents

import com.example.volunteerapp.domain.model.VolunteerClosedEventResponse

sealed class closedUiState {
    object Loading : closedUiState()
    data class Success(val events: List<VolunteerClosedEventResponse>) : closedUiState()
    data class Error(val message: String) : closedUiState()
}