package com.example.volunteerapp.domain.model;

data class RegisterRequest(
        val userLogin: String,
        val password: String,
        val userName: String,
        val userSurname: String,
        val userMiddlename: String?, // Отчество может быть nullable
        val userRole: String // "volunteer" или "organizer"
)
