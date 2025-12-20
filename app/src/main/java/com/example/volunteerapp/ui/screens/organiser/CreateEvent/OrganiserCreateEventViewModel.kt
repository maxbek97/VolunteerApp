package com.example.volunteerapp.ui.screens.organiser.CreateEvent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.volunteerapp.data.remote.OrganiserRepository
import com.example.volunteerapp.domain.model.CreateEventRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OrganiserCreateEventViewModel(
    private val repository: OrganiserRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<CreateEventUiState>(CreateEventUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun createEvent(
        title: String,
        description: String,
        start: String,
        end: String
    ) {
        viewModelScope.launch {
            _uiState.value = CreateEventUiState.Loading

            repository.createEvent(
                CreateEventRequest(
                    eventTitle = title,
                    eventDescription = description,
                    datetimeStart = start,
                    datetimeEnd = end
                )
            )
                .onSuccess {
                    _uiState.value = CreateEventUiState.Success
                }
                .onFailure {
                    _uiState.value = CreateEventUiState.Error(
                        it.message ?: "Ошибка создания события"
                    )
                }
        }
    }

    fun resetState() {
        _uiState.value = CreateEventUiState.Idle
    }
}
