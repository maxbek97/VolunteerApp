package com.example.volunteerapp.ui.screens.organiser

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.volunteerapp.R
import com.example.volunteerapp.ui.components.BottomButton
import com.example.volunteerapp.ui.components.VolunteerBottomNavigation
import com.example.volunteerapp.ui.screens.organiser.Profile.OrganiserProfileScreen
import com.example.volunteerapp.ui.screens.organiser.CreateEvent.OrganiserCreateEventScreen
import com.example.volunteerapp.ui.screens.organiser.pastEvents.OrganiserToCloseScreen
import com.example.volunteerapp.ui.screens.organiser.futureEvents.OrganiserUpcomingScreen

@Composable
private fun OrganiserContent(route: String, navController: NavHostController) {
    when (route) {
        "profile" -> OrganiserProfileScreen(navController)
        "upcoming" -> OrganiserUpcomingScreen()
        "toclose" -> OrganiserToCloseScreen()
        "create" -> OrganiserCreateEventScreen()
    }
}

@Composable
fun OrganiserHomeScreen(navController: NavHostController) {

    var selectedRoute by remember { mutableStateOf("profile") }

    val navItems = listOf(
        BottomButton("Профиль", painterResource(R.drawable.profile_circle), "profile"),
        BottomButton("Будущие события", painterResource(R.drawable.calendar), "upcoming"),
        BottomButton("Требующие закрытия", painterResource(R.drawable.closed), "toclose"),
        BottomButton("Новое событие", painterResource(R.drawable.pencil), "create"),
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
            OrganiserContent(selectedRoute, navController = navController)
        }
    }
}