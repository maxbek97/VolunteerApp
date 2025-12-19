package com.example.volunteerapp.ui.screens.organiser.CreateEvent

sealed interface CreateEventUiState {
    object Idle : CreateEventUiState
    object Loading : CreateEventUiState
    object Success : CreateEventUiState
    data class Error(val message: String) : CreateEventUiState
}