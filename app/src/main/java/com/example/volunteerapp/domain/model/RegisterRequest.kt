package com.example.volunteerapp.domain.model;

data class RegisterRequest(
        val userLogin: String,
        val password: String,
        val firstName: String,
        val lastName: String,
        val middleName: String?, // Отчество может быть nullable
        val userRole: String // "volunteer" или "organizer"
)
