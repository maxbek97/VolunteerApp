package com.example.volunteerapp.ui.screens.volunteer

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.volunteerapp.ui.components.*
import com.example.volunteerapp.R

@Composable
fun VolunteerHomeScreen() {

    // Какая вкладка выбрана
    var selectedRoute by remember { mutableStateOf("profile") }

    // Пункты панели
    val navItems = listOf(
        BottomButton("Профиль", painterResource(R.drawable.profile_circle), "profile"),
        BottomButton("Будущие события", painterResource(R.drawable.calendar), "upcoming"),
        BottomButton("Мои события", painterResource(R.drawable.list), "my"),
        BottomButton("Прошедшие события", painterResource(R.drawable.closed), "done"),
    )

    Scaffold(
        bottomBar = {
            VolunteerBottomNavigation(
                items = navItems,
                selectedRoute = selectedRoute,
                onItemClick = { item ->
                    selectedRoute = item.route
                }
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (selectedRoute) {
                "profile" -> Text("Экран профиля")
                "upcoming" -> Text("Будущие события")
                "my" -> Text("Мои события")
                "done" -> Text("Завершённые события")
            }
        }
    }
}
