package com.example.volunteerapp.ui.screens.volunteer.myEvents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.volunteerapp.data.remote.RetrofitClient
import com.example.volunteerapp.data.remote.VolunteerRepository
import com.example.volunteerapp.ui.components.EventCard
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.volunteerapp.domain.model.EventResponse
import com.example.volunteerapp.ui.components.SearchBar
import com.example.volunteerapp.ui.screens.volunteer.myEvents.MyEventDetailsDialog

@Composable
fun VolunteerMyEventsScreen() {

    val repository = remember {
        VolunteerRepository(RetrofitClient.volunteerApi)
    }

    val viewModel: VolunteerMyEventViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return VolunteerMyEventViewModel(repository) as T
            }
        }
    )

    val uiState by viewModel.uiState.collectAsState()
    var selectedEvent by remember { mutableStateOf<EventResponse?>(null) }
    var query by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.refresh()
    }

    Column {

            SearchBar(query, onQueryChange = { query = it })

            Spacer(Modifier.height(8.dp))

        when (val state = uiState) {
            myUiState.Loading -> {
                Box(Modifier.fillMaxSize(), Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is myUiState.Error -> {
                Text(state.message)
            }

            is myUiState.Success -> {
                val filtered = state.events.filter {
                    it.eventTitle.contains(query, ignoreCase = true)
                }
                LazyColumn(
                    contentPadding = PaddingValues(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(filtered) { event ->
                        EventCard(event = event, onClick = {selectedEvent = event})
                    }
                }
            }
        }
    }

    selectedEvent?.let {
        MyEventDetailsDialog(
            event = it,
            onDismiss = { selectedEvent = null }
        )
    }

}
