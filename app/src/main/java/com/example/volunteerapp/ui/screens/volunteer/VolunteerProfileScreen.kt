package com.example.volunteerapp.ui.screens.volunteer

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
import com.example.volunteerapp.ui.components.ProfileRow
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

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
            .padding(16.dp)
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
                val user = (state as ProfileUiState.Success).user

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                    ProfileRow("Логин", user.userLogin)
                    ProfileRow("Роль", user.userRole)
                    ProfileRow("Имя", user.userName)
                    ProfileRow("Фамилия", user.userSurname)

                    user.userMiddlename?.let {
                        ProfileRow("Отчество", it)
                    }

                    user.volunteersHours?.let {
                        ProfileRow("Часы волонтёрства", it.toString())
                    }

                    Spacer(Modifier.height(32.dp))

                    Button(
                        onClick = {
                            viewModel.logout()
                            navController.navigate("auth") {
                                popUpTo(0)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("Выйти из аккаунта")
                    }
                }
            }
        }
    }
}

