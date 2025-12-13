package com.example.volunteerapp.data.remote
import com.example.volunteerapp.domain.model.*

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    // POST /auth/register
    @POST("auth/register")
    suspend fun register(
        @Body body: RegisterRequest
    ): Response<RegisterResponse>

    // POST /auth/login
    @POST("auth/login")
    suspend fun login(
        @Body body: LoginRequest
    ): Response<LoginResponse>

    @GET("auth/user-info")
    suspend fun getVolunteerInfo(
        @Header("Authorization") token: String
    ): UserDto
}