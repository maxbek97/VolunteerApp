package com.example.volunteerapp.domain.model

data class UserDto(
    val idUser: UInt,
    val userLogin: String,
    val userRole: String,
    val userName: String,
    val userSurname: String,
    val userMiddlename: String?,
    val volunteersHours: Int?
)
