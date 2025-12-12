package com.example.volunteerapp.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun TopMessageBar(
    message: String,
    isError: Boolean,
    onDismiss: () -> Unit
) {
    // Время показа: 1.5 секунды
    LaunchedEffect(message) {
        if (message.isNotEmpty()) {
            delay(1500)
            onDismiss()
        }
    }

    AnimatedVisibility(
        visible = message.isNotEmpty(),
        enter = slideInVertically(initialOffsetY = { -100 }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { -100 }) + fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color.White,
                tonalElevation = 4.dp,   // лёгкая тень
                shadowElevation = 4.dp
            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = message,
                        fontSize = 16.sp,
                        color = if (isError) Color(0xFFB00020) else Color(0xFF2E7D32)
                    )
                }
            }
        }
    }
}
