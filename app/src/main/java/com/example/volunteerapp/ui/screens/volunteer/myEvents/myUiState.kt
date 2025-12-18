package com.example.volunteerapp.ui.screens.volunteer.myEvents

import com.example.volunteerapp.domain.model.EventResponse

sealed class myUiState {
    object Loading : myUiState()
    data class Success(val events: List<EventResponse>) : myUiState()
    data class Error(val message: String) : myUiState()
}