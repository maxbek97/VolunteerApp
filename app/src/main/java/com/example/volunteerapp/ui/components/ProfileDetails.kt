package com.example.volunteerapp.ui.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileDetails(
    name: String,
    surname: String,
    middlename: String?
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        ProfileRow("Имя", name)
        ProfileRow("Фамилия", surname)

        middlename?.let {
            ProfileRow("Отчество", it)
        }
    }
}
