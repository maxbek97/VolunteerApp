package com.example.volunteerapp.ui.screens.organiser.futureEvents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


sealed interface SubscribeResult {
    object Idle : SubscribeResult
    object Success : SubscribeResult
    data class Error(val message: String) : SubscribeResult
}


@Composable
fun OrganiserUpcomingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Будущие мероприятия (organiser)")
    }
}

