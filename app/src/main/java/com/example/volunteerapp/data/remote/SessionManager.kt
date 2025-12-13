package com.example.volunteerapp.data.remote

object SessionManager {

    private var accessToken: String? = null

    fun saveToken(token: String) {
        accessToken = token
    }

    fun getToken(): String? = accessToken

    fun clear() {
        accessToken = null
    }

    fun isAuthorized(): Boolean = accessToken != null
}
