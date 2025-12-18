package com.example.volunteerapp.ui.screens.volunteer.Profile

import com.example.volunteerapp.domain.model.UserDto

sealed class ProfileUiState {
    object Loading : ProfileUiState()
    data class Success(val user: UserDto) : ProfileUiState()
    data class Error(val message: String) : ProfileUiState()
}