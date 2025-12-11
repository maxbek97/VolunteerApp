package com.example.volunteerapp.domain.model

data class LoginResponse(
    val success: Boolean,
    val token: String?,
    // ВАЖНО: Согласно коду бэкенда (result.UserRole),
    // поле 'message' в ответе содержит роль пользователя.
    // Мы переименуем его для ясности в приложении.
    val message: String?
)