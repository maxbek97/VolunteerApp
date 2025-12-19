package com.example.volunteerapp.ui.screens.organiser.Profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.volunteerapp.data.remote.AuthRepository
import com.example.volunteerapp.data.remote.SessionManager
import com.example.volunteerapp.ui.screens.volunteer.Profile.ProfileUiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow


class VolunteerProfileViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            _uiState.value = ProfileUiState.Loading

            val result = repository.getVolunteerInfo()

            result
                .onSuccess { user ->
                    _uiState.value = ProfileUiState.Success(user)
                }
                .onFailure { e ->
                    _uiState.value = ProfileUiState.Error(
                        e.message ?: "Ошибка загрузки профиля"
                    )
                }
        }
    }


    fun logout() {
        SessionManager.clear()
    }
}
