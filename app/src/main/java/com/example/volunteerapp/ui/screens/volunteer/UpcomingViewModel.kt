package com.example.volunteerapp.ui.screens.volunteer

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.volunteerapp.data.remote.VolunteerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class VolunteerUpcomingViewModel(
    private val repository: VolunteerRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<UpcomingUiState>(UpcomingUiState.Loading)
    val uiState = _uiState.asStateFlow()

    var subscribeResult by mutableStateOf<SubscribeResult>(SubscribeResult.Idle)
        private set

    init {
        loadEvents()
    }

    fun refresh() {
        loadEvents()
    }

    private fun loadEvents() {
        viewModelScope.launch {
            repository.getFutureEvents()
                .onSuccess {
                    _uiState.value = UpcomingUiState.Success(it)
                }
                .onFailure {
                    _uiState.value = UpcomingUiState.Error(
                        it.message ?: "Ошибка загрузки событий"
                    )
                }
        }
    }

    fun subscribe(eventId: UInt) {
        viewModelScope.launch {
            val result = repository.subscribe(eventId)
            subscribeResult = if (result.isSuccess) {
                loadEvents()
                SubscribeResult.Success

            } else {
                SubscribeResult.Error("Произошла ошибка")
            }
        }
    }
    fun resetSubscribeResult() {
        subscribeResult = SubscribeResult.Idle
    }
}
