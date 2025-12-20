package com.example.volunteerapp.ui.screens.organiser.CreateEvent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.volunteerapp.data.remote.OrganiserRepository
import com.example.volunteerapp.data.remote.RetrofitClient

@Composable
fun OrganiserCreateEventScreen(
    onFinished: () -> Unit
) {
    val repository = remember {
        OrganiserRepository(RetrofitClient.organiserApi)
    }

    val viewModel: OrganiserCreateEventViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return OrganiserCreateEventViewModel(repository) as T
            }
        }
    )

    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        OrganiserCreateEventDialog(
            viewModel = viewModel,
            onDismiss = {
                showDialog = false
                onFinished()
            },
            onSuccess = {
                showDialog = false
                onFinished()
            }
        )
    }

    // пустой экран-заглушка
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Создание нового мероприятия")
    }
}

