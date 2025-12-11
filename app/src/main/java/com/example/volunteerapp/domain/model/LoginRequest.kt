package com.example.volunteerapp.domain.model

data class LoginRequest(
    val userLogin: String, // Название поля должно соответствовать полю в LoginDTO
    val password: String
)