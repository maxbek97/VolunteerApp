package com.example.volunteerapp.data.remote
import com.example.volunteerapp.domain.model.*

// Репозиторий принимает интерфейс ApiService
class AuthRepository(
    private val api: ApiService
) {

    // Регистрация: возвращает Result<String> (сообщение об успехе/ошибке)
    suspend fun register(dto: RegisterRequest): Result<String> =
        try {
            val response = api.register(dto)

            if (response.isSuccessful && response.body() != null) {
                // Успешный ответ (200 OK)
                Result.success(response.body()!!.message)
            } else {
                // Ошибка бэкенда (например, 400 Bad Request)
                Result.failure(Exception(response.errorBody()?.string() ?: "Ошибка регистрации"))
            }
        } catch (e: Exception) {
            // Ошибка сети (например, таймаут, нет интернета)
            Result.failure(e)
        }

    // Вход: возвращает Result<LoginResponse>
    suspend fun login(dto: LoginRequest): Result<LoginResponse> =
        try {
            val response = api.login(dto)

            if (response.isSuccessful && response.body() != null) {
                // Успешный ответ (200 OK)
                Result.success(response.body()!!)
            } else {
                // Ошибка бэкенда (например, 401 Unauthorized)
                Result.failure(Exception(response.errorBody()?.string() ?: "Ошибка входа"))
            }
        } catch (e: Exception) {
            // Ошибка сети
            Result.failure(e)
        }

    suspend fun getVolunteerInfo(): Result<UserDto> {
        val token = SessionManager.getToken()
            ?: return Result.failure(Exception("No token"))

        return runCatching {
            api.getVolunteerInfo("Bearer $token")
        }
    }
}