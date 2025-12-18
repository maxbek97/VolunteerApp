package com.example.volunteerapp.ui.screens.volunteer.myEvents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.volunteerapp.data.remote.VolunteerRepository
import com.example.volunteerapp.ui.screens.volunteer.myEvents.myUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VolunteerMyEventViewModel(
    private val repository: VolunteerRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<myUiState>(myUiState.Loading)
    val uiState = _uiState.asStateFlow()


    init {
        loadEvents()
    }

    fun refresh() {
        loadEvents()
    }

    private fun loadEvents() {
        viewModelScope.launch {
            repository.getMyEvents()
                .onSuccess {
                    _uiState.value = myUiState.Success(it)
                }
                .onFailure {
                    _uiState.value = myUiState.Error(
                        it.message ?: "Ошибка загрузки событий"
                    )
                }
        }
    }
}