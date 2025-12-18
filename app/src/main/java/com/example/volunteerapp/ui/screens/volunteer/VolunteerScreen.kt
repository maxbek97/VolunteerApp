package com.example.volunteerapp.ui.screens.volunteer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.volunteerapp.ui.components.*
import com.example.volunteerapp.R
import com.example.volunteerapp.ui.screens.volunteer.Profile.VolunteerProfileScreen
import com.example.volunteerapp.ui.screens.volunteer.closedEvents.VolunteerDoneScreen
import com.example.volunteerapp.ui.screens.volunteer.futureEvents.VolunteerUpcomingScreen
import com.example.volunteerapp.ui.screens.volunteer.myEvents.VolunteerMyEventsScreen

@Composable
private fun VolunteerContent(route: String, navController: NavHostController) {
    when (route) {
        "profile" -> VolunteerProfileScreen(navController)
        "upcoming" -> VolunteerUpcomingScreen()
        "my" -> VolunteerMyEventsScreen()
        "done" -> VolunteerDoneScreen()
    }
}

@Composable
fun VolunteerHomeScreen(navController: NavHostController) {

    var selectedRoute by remember { mutableStateOf("profile") }

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
                onItemClick = { selectedRoute = it.route }
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            VolunteerContent(selectedRoute, navController = navController)
        }
    }
}
