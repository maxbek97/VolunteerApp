package com.example.volunteerapp.ui.screens.volunteer.Profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.volunteerapp.data.remote.AuthRepository
import com.example.volunteerapp.data.remote.RetrofitClient
import com.example.volunteerapp.ui.components.*

import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.volunteerapp.ui.screens.volunteer.Profile.ProfileUiState


@Composable
fun VolunteerProfileScreen(
    navController: NavHostController
) {
    val repository = remember {
        AuthRepository(RetrofitClient.apiService)
    }

    val viewModel: VolunteerProfileViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return VolunteerProfileViewModel(repository) as T
            }
        }
    )

    val state by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        when (val uiState = state) {

            ProfileUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is ProfileUiState.Error -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Ошибка загрузки профиля",
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = uiState.message,
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
            }

            is ProfileUiState.Success -> {
                val user = uiState.user

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    ProfileHeader()

                    // ====== ВЕРХНИЙ КОНТЕНТ ======
                    Column(
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                        modifier = Modifier.padding(12.dp)

                    ) {



                        ProfileStatsRow(
                            login = user.userLogin,
                            role = user.userRole,
                            hours = user.volunteersHours?.toString() ?: "0"
                        )

                        ProfileDetails(
                            name = user.userName,
                            surname = user.userSurname,
                            middlename = user.userMiddlename
                        )
                    }

                    // ====== КНОПКА ВЫХОДА ======
                    LogoutButton {
                        viewModel.logout()
                        navController.navigate("auth") {
                            popUpTo(0)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}