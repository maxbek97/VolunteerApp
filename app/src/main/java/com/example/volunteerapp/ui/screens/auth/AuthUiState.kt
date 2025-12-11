package com.example.volunteerapp.ui.screens.auth

sealed class AuthUiState {
    object Idle : AuthUiState() // Начальное состояние, бездействует
    object Loading : AuthUiState() // Идет сетевой запрос
    object RegistrationSuccess : AuthUiState() // Успешная регистрация
    data class LoginSuccess(val userRole: String) : AuthUiState() // Успешный вход + роль
    data class Error(val message: String) : AuthUiState() // Ошибка (сеть, бэкенд и т.д.)
}