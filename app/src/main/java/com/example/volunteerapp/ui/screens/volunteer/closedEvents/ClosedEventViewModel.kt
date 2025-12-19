package com.example.volunteerapp.ui.screens.volunteer.closedEvents

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.volunteerapp.data.remote.VolunteerRepository
import com.example.volunteerapp.ui.screens.volunteer.futureEvents.SubscribeResult
import com.example.volunteerapp.ui.screens.volunteer.futureEvents.UpcomingUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClosedEventViewModel(
    private val repository: VolunteerRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<closedUiState>(closedUiState.Loading)
    val uiState = _uiState.asStateFlow()


    init {
        loadEvents()
    }

    fun refresh() {
        loadEvents()
    }

    private fun loadEvents() {
        viewModelScope.launch {
            repository.getClosedEvents()
                .onSuccess {
                    _uiState.value = closedUiState.Success(it)
                }
                .onFailure {
                    _uiState.value = closedUiState.Error(
                        it.message ?: "Ошибка загрузки событий"
                    )
                }
        }
    }

}