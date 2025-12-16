package com.example.volunteerapp.ui.screens.volunteer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.volunteerapp.data.remote.RetrofitClient
import com.example.volunteerapp.data.remote.VolunteerRepository
import com.example.volunteerapp.domain.model.EventResponse
import com.example.volunteerapp.ui.components.EventCard
import com.example.volunteerapp.ui.components.SearchBar
import com.example.volunteerapp.ui.screens.volunteer.modal.EventDetailsDialog
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.zIndex
import com.example.volunteerapp.ui.components.TopMessageBar


sealed interface SubscribeResult {
    object Idle : SubscribeResult
    object Success : SubscribeResult
    data class Error(val message: String) : SubscribeResult
}

@Composable
fun VolunteerUpcomingScreen() {

    val repository = remember {
        VolunteerRepository(RetrofitClient.volunteerApi)
    }

    val viewModel: VolunteerUpcomingViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return VolunteerUpcomingViewModel(repository) as T
            }
        }
    )

    val uiState by viewModel.uiState.collectAsState()
    val subscribeResult = viewModel.subscribeResult

    LaunchedEffect(Unit) {
        viewModel.refresh()
    }

    var query by remember { mutableStateOf("") }
    var selectedEvent by remember { mutableStateOf<EventResponse?>(null) }

    var message by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var messageVisible by remember { mutableStateOf(false) }

    // 👉 Реакция на подписку
    LaunchedEffect(subscribeResult) {
        when (subscribeResult) {
            is SubscribeResult.Success -> {
                message = "Заявка подана"
                isError = false
                messageVisible = true
                viewModel.resetSubscribeResult()
            }

            is SubscribeResult.Error -> {
                message = subscribeResult.message
                isError = true
                messageVisible = true
                viewModel.resetSubscribeResult()
            }

            else -> Unit
        }
    }

    Box(Modifier.fillMaxSize()) {

        TopMessageBar(
            message = message,
            isError = isError,
            visible = messageVisible,
            onAutoHide = { messageVisible = false },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .zIndex(1f  )
        )

        Column {

            SearchBar(query, onQueryChange = { query = it })

            Spacer(Modifier.height(8.dp))

            when (val state = uiState) {
                UpcomingUiState.Loading -> {
                    Box(Modifier.fillMaxSize(), Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is UpcomingUiState.Error -> {
                    Text(state.message)
                }

                is UpcomingUiState.Success -> {
                    val filtered = state.events.filter {
                        it.eventTitle.contains(query, ignoreCase = true)
                    }

                    LazyColumn(
                        contentPadding = PaddingValues(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(filtered) { event ->
                            EventCard(
                                event = event,
                                onClick = { selectedEvent = event }
                            )
                        }
                    }
                }
            }
        }
    }

    selectedEvent?.let {
        EventDetailsDialog(
            event = it,
            onDismiss = { selectedEvent = null },
            onSubscribe = {
                viewModel.subscribe(it.id)
                selectedEvent = null
            }
        )
    }
}

