package com.example.volunteerapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.volunteerapp.ui.screens.auth.AuthScreen
import com.example.volunteerapp.ui.screens.organiser.OrganiserHomeScreen
import com.example.volunteerapp.ui.screens.volunteer.VolunteerHomeScreen

@Composable
fun MainNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "auth"
    ) {
        composable("auth") {
            AuthScreen(navController = navController)
        }

        composable("volunteer_home") {
            VolunteerHomeScreen(navController)
        }

        composable("organiser_home") {
            OrganiserHomeScreen(navController)
        }
    }
}
