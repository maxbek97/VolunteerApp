package com.example.volunteerapp.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.volunteerapp.data.remote.AuthRepository
import com.example.volunteerapp.domain.model.LoginRequest
import com.example.volunteerapp.domain.model.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// ViewModel теперь принимает репозиторий через конструктор
class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    // Состояние, которое будет "слушать" AuthScreen
    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState

    fun register(
        userLogin: String, userPassword: String, userRole: String,
        firstName: String, lastName: String, middleName: String?
    ) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading

            val dto = RegisterRequest(
                userLogin, userPassword, firstName,
                lastName, middleName, userRole
            )

            // Вызов через Репозиторий
            authRepository.register(dto)
                .onSuccess {
                    _uiState.value = AuthUiState.RegistrationSuccess
                }
                .onFailure { error ->
                    _uiState.value = AuthUiState.Error(error.message ?: "Ошибка регистрации")
                }
        }
    }

    fun login(userLogin: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading

            // Вызов через Репозиторий
            authRepository.login(LoginRequest(userLogin, password))
                .onSuccess { loginResponse ->
                    // Получаем роль, которую мы извлекли из поля "message" в LoginResponse
                    val role = loginResponse.message ?: "volunteer"
                    _uiState.value = AuthUiState.LoginSuccess(role)
                }
                .onFailure { error ->
                    _uiState.value = AuthUiState.Error(error.message ?: "Ошибка входа")
                }
        }
    }
}